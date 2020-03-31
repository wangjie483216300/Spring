package com.CloudDinner.Dao;

import com.CloudDinner.Model.MENU_TABLE;
import com.CloudDinner.Model.Message.MENU_Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {
    /**
     * 新增菜单信息(不可能有两种相同的菜名,前端用set去重</>)
     * @param menu_table
     * @return
     */
    int insertMenue(MENU_TABLE menu_table);

    /**
     * 查询店家菜单信息
     * @param id
     * @return
     */
    List<MENU_TABLE> selectMenu(@Param("ID") String id);





    //更新主食及其价格
    int updateMAIN_FOOD(MENU_Message menu_message);
    //更新凉菜及其价格
    int updateCOOL_FOOD(MENU_Message menu_message);
    //更新酒水及其价格
    int updateWINE(MENU_Message menu_message);
    //更新甜品及其价格
    int updateSWEET(MENU_Message menu_message);



    //删除主食及其价格
    int deleteMAIN_FOOD(MENU_TABLE menu_table);
    //删除凉菜及其价格
    int deleteCOOL_FOOD(MENU_TABLE menu_table);
    //删除酒水及其价格
    int deleteWINE(MENU_TABLE menu_table);
    //删除甜品及其价格
    int deleteSWEET(MENU_TABLE menu_table);
    //全部删除及其价格
    int deleteAll(MENU_TABLE menu_table);


}
