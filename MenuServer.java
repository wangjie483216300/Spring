package com.CloudDinner.Service;

import com.CloudDinner.Dao.MenuDao;
import com.CloudDinner.Model.MENU_TABLE;
import com.CloudDinner.Model.Message.MENU_Message;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServer {
    @Autowired
    private MenuDao menuDao;

    /**
     * 新增菜单信息(不可能有两种相同的菜名,前端用set去重</>)
     * @param menu_table
     * @return
     */
    public String insertMenue(MENU_TABLE menu_table){
        String Message = "";
        try {
            int status = menuDao.insertMenue(menu_table);
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
     * 查询店家菜单信息
     * @param id
     * @return
     */
    public List<MENU_TABLE> selectMenu(String id){
        List<MENU_TABLE> list = new ArrayList<MENU_TABLE>();
        try {
            list = menuDao.selectMenu(id);
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 更新主食及其价格
     * @param menu_message
     * @return
     */
    public String updateMAIN_FOOD(MENU_Message menu_message){
        String Message = "" ;
        try{
            int status = menuDao.updateMAIN_FOOD(menu_message);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 更新凉菜及其价格
     * @param menu_message
     * @return
     */
    public String updateCOOL_FOOD(MENU_Message menu_message){
        String Message = "";
        try {
            int status  = menuDao.updateCOOL_FOOD(menu_message);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 更新酒水及其价格
     * @param menu_message
     * @return
     */
    public String updateWINE(MENU_Message menu_message){
        String Message = "";
        try {
            int status  = menuDao.updateWINE(menu_message);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 更新甜品及其价格
     * @param menu_message
     * @return
     */
    public String updateSWEET(MENU_Message menu_message){
        String Message = "";
        try {
            int status  = menuDao.updateSWEET(menu_message);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 删除主食及其价格
     * @param menu_table
     * @return
     */
    public String deleteMAIN_FOOD(MENU_TABLE menu_table){
        String Message = "";
        try {
            int status  = menuDao.deleteMAIN_FOOD(menu_table);
            System.out.println(status);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 删除凉菜及其价格
     * @param menu_table
     * @return
     */
    public String deleteCOOL_FOOD(MENU_TABLE menu_table){
        String Message = "";
        try {
            int status  = menuDao.deleteCOOL_FOOD(menu_table);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }
    /**
     * 删除酒水及其价格
     * @param menu_table
     * @return
     */
    public String deleteWINE(MENU_TABLE menu_table){
        String Message = "";
        try {
            int status  = menuDao.deleteWINE(menu_table);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }
    /**
     * 删除甜品及其价格
     * @param menu_table
     * @return
     */
    public String deleteSWEET(MENU_TABLE menu_table){
        String Message = "";
        try {
            int status  = menuDao.deleteSWEET(menu_table);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }

    /**
     * 全部删除及其价格
     * @param menu_table
     * @return
     */
    public String deleteAll(MENU_TABLE menu_table){
        String Message = "";
        try {
            int status  = menuDao.deleteAll(menu_table);
            if(status == 1){
                Message = "操作成功!" ;
            }else {
                Message = "操作失败" ;
            }
        } catch (Exception e) {
            Message = "错误,请重新操作!" ;
            e.printStackTrace();
        }
        return Message;
    }

}
