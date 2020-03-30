package com.CloudDinner.Service;

import com.CloudDinner.Dao.PersonFriendCrudDao;
import com.CloudDinner.Model.FRIEND_STATUS_TABLE;
import com.CloudDinner.Model.FRIEND_TABLE;
import com.CloudDinner.Model.PERSONAL_TABLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendCrudServer {
    @Autowired
    private PersonFriendCrudDao personFriendCrudDao;

    /**
     * 通过id查询某一个用户(用于加好友)
     * @param id
     * @return
     */
    public PERSONAL_TABLE selectuserone(String id){
        PERSONAL_TABLE personal_table = new PERSONAL_TABLE();
        try {
            Object object = personFriendCrudDao.selectuserone(id) ;
            if (object == null){
                personal_table.setMessage("查询失败!");
            }else {
                personal_table = (PERSONAL_TABLE) object;
                personal_table.setMessage("查询成功");
            }
        } catch (Exception e) {
            personal_table.setMessage("错误,请重新操作!");
            e.printStackTrace();
        }
        return personal_table;
    }
    /**
     * 发送方加好友(如果向同一个用户发送多个好友请求,数据表内也只插入一条数据)
     * 加好友之前先查询好友表,判断双方是否为好友关系,并查询是否已有记录(被拒绝)
     * 这个有friendone和friendtwo,得用or,都满足关系
     * @param friend_status_table
     * @return
     */
    public String addFriend(FRIEND_STATUS_TABLE friend_status_table){
        String Message = "" ;
        try {
            String f1 = friend_status_table.getSEND_ID();
            String f2 = friend_status_table.getRECEIVE_ID();
            FRIEND_TABLE friend_table = new FRIEND_TABLE();
            friend_table.setFRIEND_ID_ONE(f1);
            friend_table.setFRIEND_ID_TWO(f2);
            if (personFriendCrudDao.selectFriendone(friend_table)!=null){
                Message = "您与对方已经是好友,请勿重复添加";
            }else {
                //不是好友 1,没发请求消息   2,被拒绝
                //发送方查询某一个请求是否存在
                Object object = personFriendCrudDao.selectFriendStatusOne(friend_status_table);
                if (object==null){
                    //不存在直接插入数据(发送好友请求)
                        try {
                            int status = personFriendCrudDao.addFriend(friend_status_table);
                            if (status==1){
                                Message = "操作成功!";
                            }else {
                                Message = "操作失败!" ;
                            }
                        } catch (Exception e) {
                            Message = "错误,请重新操作!";
                            e.printStackTrace();
                        }
                }else {
                    //存在(被拒绝),更新数据,相当于再发一次好友请求
                    int status = personFriendCrudDao.updateStatus(friend_status_table);
                    try {
                        if (status==1){
                            Message = "操作成功!";
                        }else {
                            Message = "操作失败!";
                        }
                    } catch (Exception e) {
                        Message = "错误,请重新操作!";
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message ;
    }

    /**
     * 查看自己好友信息
     * @param friend_table
     * @return
     */
    public List<FRIEND_TABLE> selectFriends(FRIEND_TABLE friend_table){
        List<FRIEND_TABLE> list = new ArrayList<FRIEND_TABLE>();
        try {
            list = personFriendCrudDao.selectFriends(friend_table);
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查看自己的好友请求通知
     * @param friend_status_table
     * @return
     */
    public  List<FRIEND_STATUS_TABLE> selectFriendStatus(FRIEND_STATUS_TABLE friend_status_table){
        List<FRIEND_STATUS_TABLE> list = new ArrayList<FRIEND_STATUS_TABLE>();
        try {
            list = personFriendCrudDao.selectFriendStatus(friend_status_table);
        } catch (Exception e) {
            list = null ;
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 同意或拒绝好友请求
     * @param friend_status_table
     * @return
     */
    public String AgreeStatus(FRIEND_STATUS_TABLE friend_status_table){
        String Message = "";
        try {
            int status = personFriendCrudDao.AgreeStatus(friend_status_table);

            if (status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败!";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 接收方同意后(调用接口)自动将双方数据插入好友表(双方成为好友),不同意,不插入.
     * @param friend_table
     * @return
     */
    public String insertFriend(FRIEND_TABLE friend_table){
        String Message = "";
        try {
            String FRIEND_NAME_ONE = personFriendCrudDao.selectusername(friend_table.getFRIEND_ID_ONE());
            String FRIEND_NAME_TWO = personFriendCrudDao.selectusername(friend_table.getFRIEND_ID_TWO());
            friend_table.setFRIEND_NAME_ONE(FRIEND_NAME_ONE);
            friend_table.setFRIEND_NAME_TWO(FRIEND_NAME_TWO);
            int status = personFriendCrudDao.insertFriend(friend_table);
                if (status==1){
                    Message = "操作成功!";
                }else {
                    Message = "操作失败!";
                }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 更新好友备注
     * @param friend_table
     * @return
     */
    public String updateRemark(FRIEND_TABLE friend_table){
        String Message = "";
        System.out.println(friend_table);
        try{
            int status1 = personFriendCrudDao.updateRemarkOne(friend_table);
            System.out.println(status1);
            if (status1==1){
                Message = "操作成功!";
            }else {
                int status2 = personFriendCrudDao.updateRemarkTwo(friend_table);
                System.out.println(status2);
                if (status2==1){
                    Message = "操作成功!";
                }else {
                    Message = "操作失败!";
                }
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 删除好友-->删除好友后,对好友状态记录进行删除
     * @param friend_table
     * @return
     */
    public String deleteFriend(FRIEND_TABLE friend_table){
        String Message = "";
        try{
            FRIEND_STATUS_TABLE friend_status_table = new FRIEND_STATUS_TABLE();
            friend_status_table.setSEND_ID(friend_table.getFRIEND_ID_ONE());
            friend_status_table.setRECEIVE_ID(friend_table.getFRIEND_ID_TWO());
            int status = personFriendCrudDao.deleteFriend(friend_table);
            if (status==1){
                personFriendCrudDao.deleteStatus(friend_status_table);
                Message = "操作成功!";
            }else {
                Message = "操作失败!";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
}
