package com.CloudDinner.Dao;

import com.CloudDinner.Model.FRIEND_STATUS_TABLE;
import com.CloudDinner.Model.FRIEND_TABLE;
import com.CloudDinner.Model.PERSONAL_TABLE;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonFriendCrudDao {

    /**
     * 发送方加好友(如果向同一个用户发送多个好友请求,数据表内也只插入一条数据)
     * 加好友之前先查询好友表,判断双方是否为好友关系,是否已经发送请求
     * 这个有friendone和friendtwo,得用or,都满足关系
     * @param FRIEND_status_table
     * @return
     * insert语句
     */
    int addFriend(FRIEND_STATUS_TABLE FRIEND_status_table);
    /**
     * 通过id查询某一个用户(用于加好友)
     * @param id
     * @return
     */
    PERSONAL_TABLE selectuserone(@Param("ID")String id);

    /**
     * 查看好友信息在(1,查看好友信息时
     * @param friend_table
     * @return
     * select语句
     */
    List<FRIEND_TABLE> selectFriends(FRIEND_TABLE friend_table);
    /**
     * 查看某一个用户是否是自己好友(2,发送好友请求前    3,删除好友后)
     * @param friend_table
     * @return
     */
    FRIEND_TABLE selectFriendone(FRIEND_TABLE friend_table);

    /**
     * 发送方 or 接收方查看 "我的消息"页面,查看请求信息列表
     * @param friend_status_table 好友请求 or 回应 列表
     * @return
     * select语句
     */
    List<FRIEND_STATUS_TABLE> selectFriendStatus(FRIEND_STATUS_TABLE friend_status_table);

    /**
     * 接收方同意或拒绝好友请求
     * @param FRIEND_status_table
     * @return
     * update语句
     */
    int AgreeStatus(FRIEND_STATUS_TABLE FRIEND_status_table);

    /**
     * 接收方同意后自动将双方数据插入好友表(双方成为好友),不同意,不插入.
     * @param friend_table
     * @return
     * insert语句
     */
    int insertFriend(FRIEND_TABLE friend_table);
    //根据id获取username
    String selectusername(@Param("ID")String id);

    /**
     * 执行此方法之前,先判断是否存在记录(即查询一次状态selectFriendStatusOne)有则更新,没有则插入
     * 发送方第 N (n!=1)次发送请求,记录存在,使用update更新记录
     * @param FRIEND_status_table
     * @return
     * update语句
     */
    int updateStatus(FRIEND_STATUS_TABLE FRIEND_status_table);
    //发送方查询某一个请求是否存在
    FRIEND_STATUS_TABLE selectFriendStatusOne(FRIEND_STATUS_TABLE friend_status_table);

    /**
     * 修改好友备注--->ID1 改 Remarkname2 , ID2 改 Remarkame1
     * @param friend_table
     * @return
     * update语句
     */
    int updateRemarkOne(FRIEND_TABLE friend_table);
    int updateRemarkTwo(FRIEND_TABLE friend_table);

    /**
     * 删除好友,同时要删除消息状态记录
     * @param friend_table
     * @return
     * delete语句
     */
    int deleteFriend(FRIEND_TABLE friend_table);

    /**
     * 删除好友后,对好友状态记录进行删除
     * @param FRIEND_status_table
     * @return
     * delete语句
     */
    int deleteStatus(FRIEND_STATUS_TABLE FRIEND_status_table);
}
