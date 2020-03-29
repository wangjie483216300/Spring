package com.CloudDinner.Dao;

import com.CloudDinner.Model.SHOP_TABLE;
import org.apache.ibatis.annotations.Param;

public interface ShopCrudDao {
    /**
     * 查看商店基本信息
     * @param id
     * @return
     */
    SHOP_TABLE selectShop(@Param("id")String id);

    /**
     * 更新商店信息
     * @param shop_table
     * @return
     */
    int updateshop(SHOP_TABLE shop_table);

}
