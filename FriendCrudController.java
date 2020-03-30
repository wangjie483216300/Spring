package com.CloudDinner.Controller;

import com.CloudDinner.Model.FRIEND_STATUS_TABLE;
import com.CloudDinner.Model.FRIEND_TABLE;
import com.CloudDinner.Service.FriendCrudServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.CloudDinner.Controller.ControllerUtil.ModelAndViewUtil.getMv;
import static com.CloudDinner.Controller.ControllerUtil.RequestUtil.getParam;
import static com.CloudDinner.Controller.ControllerUtil.RequestUtil.setRequest;

@RestController
public class FriendCrudController {
    @Autowired
    private FriendCrudServer friendCrudServer;

    /**
     * 通过id查询某一个用户(用于加好友)
     * @return
     */
    @PostMapping("/selectuserone")
    public ModelAndView selectuserone(HttpServletRequest request){
        setRequest(request);
        String id = getParam("id");
        System.out.println(id);
        return getMv(friendCrudServer.selectuserone(id));
    }

    /**
     * 发送方加好友(如果向同一个用户发送多个好友请求,数据表内也只插入一条数据)
     * 加好友之前先查询好友表,判断双方是否为好友关系,并查询是否已有记录(被拒绝)
     * 这个有friendone和friendtwo,得用or,都满足关系
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/addFriend")
    public ModelAndView addFriend(HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("Message");
        FRIEND_STATUS_TABLE friend_status_table = new FRIEND_STATUS_TABLE();
        friend_status_table.setSEND_ID(request.getParameter("SEND_ID"));
        friend_status_table.setRECEIVE_ID(request.getParameter("RECEIVE_ID"));
        modelAndView.addObject("Message",friendCrudServer.addFriend(friend_status_table));
        return modelAndView;
    }

    /**
     * 查看自己的好友信息
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/selectFriends")
    public ModelAndView selectFriends(HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("Message");
        FRIEND_TABLE friend_table = new FRIEND_TABLE();
        friend_table.setFRIEND_ID_ONE(request.getParameter("ID1"));
        friend_table.setFRIEND_ID_TWO(request.getParameter("ID2"));
        modelAndView.addObject("Message",friendCrudServer.selectFriends(friend_table));
        return modelAndView;
    }

    /**
     * 查看自己的好友通知信息
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/selectFriendStatus")
    public ModelAndView selectFriendStatus(HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("Message");
        FRIEND_STATUS_TABLE friend_status_table = new FRIEND_STATUS_TABLE();
        friend_status_table.setSEND_ID(request.getParameter("ID1"));
        friend_status_table.setRECEIVE_ID(request.getParameter("ID2"));
        modelAndView.addObject("Message",friendCrudServer.selectFriendStatus(friend_status_table));
        return modelAndView;
    }


    /**
     * 同意或拒绝好友请求
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/AgreeStatus")
    public ModelAndView AgreeStatus(HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("Message");
        FRIEND_STATUS_TABLE friend_status_table = new FRIEND_STATUS_TABLE();
        friend_status_table.setSEND_ID(request.getParameter("SEND_ID"));
        friend_status_table.setRECEIVE_ID(request.getParameter("RECEIVE_ID"));
        friend_status_table.setRECEIVE_AGREE(request.getParameter("RECEIVE_AGREE"));
        modelAndView.addObject("Message",friendCrudServer.AgreeStatus(friend_status_table));
        return modelAndView;
    }

    /**
     * 接收方同意后(调用接口)自动将双方数据插入好友表(双方成为好友),不同意,不插入.
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/insertFriend")
    public ModelAndView insertFriend(HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("Message");
        FRIEND_TABLE friend_table = new FRIEND_TABLE();
        friend_table.setFRIEND_ID_ONE(request.getParameter("FRIEND_ID_ONE"));
        friend_table.setFRIEND_ID_TWO(request.getParameter("FRIEND_ID_TWO"));
        modelAndView.addObject("Message",friendCrudServer.insertFriend(friend_table));
        return modelAndView;
    }

    /**
     * 更新好友备注
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/updateRemark")
    public ModelAndView updateRemark(HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("Message");
        FRIEND_TABLE friend_table = new FRIEND_TABLE();
        friend_table.setFRIEND_ID_ONE(request.getParameter("ID1"));
        friend_table.setFRIEND_ID_TWO(request.getParameter("ID2"));
        friend_table.setFRIEND_NAME_ONE(request.getParameter("remarkname"));
        System.out.println(friend_table);
        modelAndView.addObject("Message",friendCrudServer.updateRemark(friend_table));
        return modelAndView;
    }

    /**
     * 删除好友-->删除好友后,对好友状态记录进行删除
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/deleteFriend")
    public ModelAndView deleteFriend(HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("Message");
        FRIEND_TABLE friend_table = new FRIEND_TABLE();
        friend_table.setFRIEND_ID_ONE(request.getParameter("ID1"));
        friend_table.setFRIEND_ID_TWO(request.getParameter("ID2"));
        modelAndView.addObject("Message",friendCrudServer.deleteFriend(friend_table));
        return modelAndView;
    }
}
