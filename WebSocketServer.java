package com.CloudDinner.WebSocket;

import com.CloudDinner.Dao.TeamDao;
import com.CloudDinner.Model.Message.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;


@Component//放入ioc容器
@ServerEndpoint(value = "/ws/{teamID_userid}")//将目前类定义成一个websocket服务端-加上teamid
public class WebSocketServer {
    //存储在线用户
    private static List<WebSocketServer> webSocketUsers = new CopyOnWriteArrayList<WebSocketServer>();
    //存储房间号
    private static final Map<String, CopyOnWriteArraySet<WebSocketServer>> rooms = new HashMap<>();
    //消息记录map
    private static final Map<String, List<WebSocketMessage>> webSocketMessage = new HashMap<>();
    //消息记录list
    private  List<WebSocketMessage> messageList = new ArrayList<>();
    //将用户对象存入map
    private static final Map<String,WebSocketServer> mapuser = new HashMap<>();
    private static RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        WebSocketServer.redisTemplate=redisTemplate;
    }
    //websocket的session会话
    private Session session;
    private String userId;
    private String teamId;


    private static TeamDao teamDao;
    @Autowired
    public void setTeamDao(TeamDao teamDao){
        WebSocketServer.teamDao=teamDao;
    }

    private String username;


    @PostConstruct
    public void init() {
        System.out.println("websocket 加载");
    }
   /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("teamID_userid")String teamID_userid) {
        try {
            this.session = session;
            String[] param = teamID_userid.split("_");
            this.teamId=param[0];
            this.userId=param[1];
            this.username = teamDao.selectName(userId);
            CopyOnWriteArraySet<WebSocketServer> friends = rooms.get(teamId);
            if (friends == null) {
                synchronized (rooms) {
                    if (!rooms.containsKey(teamId)) {
                        friends = new CopyOnWriteArraySet<>();
                        rooms.put(teamId, friends);
                    }
                }
            }
            friends.add(this);
            //将对象存入map
            mapuser.put(this.userId,this);
            SystemSendMessage(this.session," 加入群聊!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        try {
            System.out.println("离开");
            //用户给团队里每一个用户发信息
            CopyOnWriteArraySet<WebSocketServer> friends = rooms.get(teamId);
            if (friends != null) {
                for (WebSocketServer item : friends) {
                    if (item!=this) {
                        item.session.getAsyncRemote().sendText("系统消息:"+username+"离开群聊!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CopyOnWriteArraySet<WebSocketServer> friends = rooms.get(teamId);
        if (friends != null) {
            //删除该信息
            mapuser.remove(this.userId);
            friends.remove(this);
        }
        //群聊解散情况
        if (friends == null) {
            /**清空redis(消息记录)*/
            for (int i=0;i<redisTemplate.opsForList().size(teamId);i++){
                System.out.println(redisTemplate.opsForList().rightPop(teamId));
            }
        }
    }
    /**
     * 收到客户端消息后调用的方法
     *客户端发送过来的消息
     */
    @OnMessage//message = message+"|"+somebodyid (somebody默认为NULL)
    public void onMessage(Session session, final String Message ) {
        String[] strings = Message.split("&_&");
        final String message = strings[0];
        String someBodyId = strings[1];
        String soneBodyName = teamDao.selectName(someBodyId);
        WebSocketServer someOne= mapuser.get(someBodyId);
        if (someOne!=null){
            //用户特定用户发信息(私聊)
            CopyOnWriteArraySet<WebSocketServer> friends = rooms.get(teamId);
            if (friends != null) {
                for (WebSocketServer item : friends) {
                    if (item==someOne) {
                        item.session.getAsyncRemote().sendText(username + " to "+soneBodyName+" : " + message);
                    }
                }
            }
        }else {
            //用户给团队里每一个用户发信息
            CopyOnWriteArraySet<WebSocketServer> friends = rooms.get(teamId);
            if (friends != null) {
                for (WebSocketServer item : friends) {
                    if (item!=this) {
                        item.session.getAsyncRemote().sendText(username + "to everyone:" + message);
                    }
                }
            }
        }
        //新建线程来保存用户聊天信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                saveMessage(userId,teamId,message,new Date());//将消息记录存入redis
            }
        }).start();
    }
    /**
     * 出现错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误"+error.getMessage()+new Date());
        error.printStackTrace();
    }
    /**
     * 系统发送消息
     * @param session1
     * @throws IOException
     */
    public void SystemSendMessage(Session session1,String message) throws IOException{
        if(session1.isOpen()){
            //用户给团队里每一个用户发信息
            CopyOnWriteArraySet<WebSocketServer> friends1 = rooms.get(teamId);
            if (friends1 != null) {
                for (WebSocketServer item : friends1) {
                    if (item!=this) {
                        item.session.getAsyncRemote().sendText( "系统消息:"+this.username+ message);
                    }
                }
            }
        }
    }
    /**
     * 将消息存入消息记录里面
     * @param userId
     * @param teamId
     * @param Message
     * @param date
     */
    public static void saveMessage(String userId, String teamId, String Message,Date date){
        WebSocketMessage webSocketMessage = new WebSocketMessage();
        webSocketMessage.setUserId(userId);
        webSocketMessage.setMessage(Message);
        webSocketMessage.setTime(date);
        /**放redis里面-id为 teamid_userid*/
        redisTemplate.opsForList().leftPush(teamId,webSocketMessage.toString());
    }
}
