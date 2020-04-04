package com.CloudDinner.Dao;

import com.CloudDinner.Model.ORDER_TABLE;
import com.CloudDinner.Model.ORDER_TEAM_TABLE;
import org.apache.ibatis.annotations.Param;

public interface OrderDao {
    /**
     * 生成总订单-在提交之后生成
     * @param team_order_table
     * @return
     */
    int insertTeamOrder(ORDER_TEAM_TABLE team_order_table);


    /**
     * 生成订单-订单ID(团队ID+商店ID),团队创建好自动生成订单(9个分订单)
     * @param order_table
     * @return
     */
    int insertOrder(ORDER_TABLE order_table);
    //id查询address,telephone
    String selectUSER_ADDRESS(@Param("userID")String userID);
    String selectUSER_TELEPHONE(@Param("userID")String userID);
    String selectSHOP_ADDRESS(@Param("shopID")String shopID);
    String selectSHOP_TELEPHONE(@Param("shopID")String shopID);

    /**
     * 查看自己订单(分订单)
     * @param ID
     * @return
     */
    ORDER_TABLE selectOrder(@Param("USER_ID")String ID);

    /**
     * 用户查看总订单
     * @param ID
     * @return
     */
    ORDER_TEAM_TABLE selectTeamOrder(@Param("USER_ID")String ID);

    /**
     * 给自己订单添加东西
     * @param order_table
     * @return
     */
    int addPersonFood(ORDER_TABLE order_table);

    /**
     * 总订单添加信息
     * @param team_order_table
     * @return
     */
    int addTeamFood(ORDER_TEAM_TABLE team_order_table);

    /**
     *  所有事情(吃完饭,会议完成,队伍解除)后,删除分订单(即将STATUS字段改成NO)
     * @param ID
     * @return
     */
    int deleteUserOrder(@Param("ID")String ID);

    /**
     * 所有事情(吃完饭,会议完成,队伍解除)后,删除总订单(即将STATUS字段改成NO)
     * @param ID
     * @return
     */
    int deleteOrder(@Param("ID")String ID);

}
