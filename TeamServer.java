package com.CloudDinner.Service;

import com.CloudDinner.Dao.TeamDao;
import com.CloudDinner.Model.TEAM_TABLE;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServer {
    @Autowired
    private TeamDao teamDao;

    /**
     * 加入或者建立队伍之前查询该成员是否已经在某一队伍中
     * @param ID
     * @return
     */
    public String selectUserinTeam(String ID){
        String Message = "";
        try {
            Message = teamDao.selectUserinTeam(ID);
            if (Message==null){
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 队长建立团队
     * @param CAPTAIN_ID
     * @return
     */
    public String insertTeam(String CAPTAIN_ID){
        String Message  = "";
        //生成团队id
        String teamid = String.valueOf((Math.random()*9+1)*100000)+CAPTAIN_ID;
        String CAPTAIN_NAME;
        try {
            CAPTAIN_NAME = teamDao.selectName(CAPTAIN_ID);
            int status = teamDao.insertTeam(teamid,CAPTAIN_ID,CAPTAIN_NAME);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 新增队员
     * @param teamId
     * @param userId
     * @return
     */
    public String updateTeamMember1(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember1(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String updateTeamMember2(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember2(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String updateTeamMember3(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember4(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String updateTeamMember4(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember4(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String updateTeamMember5(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember6(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String updateTeamMember6(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember6(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String updateTeamMember7(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember7(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String updateTeamMember8(String teamId , String userId){
        String Message  = "";
        try {
            String username = teamDao.selectName(userId);
            int status = teamDao.updateTeamMember8(teamId,userId,username);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 删除队员(自己退出,或者队长踢出去)
     * @param teamId
     * @param userId
     * @return
     */
    public String deleteTeamMember1(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember1(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String deleteTeamMember2(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember2(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String deleteTeamMember3(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember3(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String deleteTeamMember4(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember4(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String deleteTeamMember5(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember5(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String deleteTeamMember6(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember6(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String deleteTeamMember7(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember7(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }
    public String deleteTeamMember8(  String teamId , String userId  ){
        String Message  = "";
        try {
            int status = teamDao.deleteTeamMember8(teamId,userId);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }


    /**
     * 某人--查看队伍信息(先根据userid查id,再用id查)
     * @param userid
     * @return
     */
    public TEAM_TABLE selectTeam(String userid){
        TEAM_TABLE team_table = new TEAM_TABLE();
        try {
            String teamId = teamDao.selectTeamId(userid);
            Object object = teamDao.selectTeam(teamId);
            if(object==null){
                team_table.setMessage("操作失败!");
            }else {
                team_table.setMessage("操作成功!");
            }
        } catch (Exception e) {
            team_table.setMessage("错误,请重新操作!");
            e.printStackTrace();
        }
        return team_table;
    }

    /**
     * 删除队伍,队长关闭团队.或者队长退出团队时
     * @param CAPTAIN_ID
     * @return
     */
    public String deleteTeam(String CAPTAIN_ID){
        String Message  = "";
        try {
            String teamId = teamDao.selectTeamId(CAPTAIN_ID);
            int status = teamDao.deleteTeam(teamId,CAPTAIN_ID);
            if(status==1){
                Message = "操作成功!";
            }else {
                Message = "操作失败";
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!";
            e.printStackTrace();
        }
        return Message;
    }

}
