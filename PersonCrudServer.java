package com.CloudDinner.Service;

import com.CloudDinner.Dao.PersonCrudDao;
import com.CloudDinner.Model.PERSONAL_TABLE;
import com.CloudDinner.Model.Message.updateUserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonCrudServer {
    @Autowired
    private PersonCrudDao personCrudDao;

    /**
     * 查看用户个人信息
     * @param id
     * @return
     */
    public PERSONAL_TABLE selectUser(String id){
        PERSONAL_TABLE personal_table = new PERSONAL_TABLE();
        Object object = personCrudDao.selectUser(id);
        if (object!=null){
            personal_table = (PERSONAL_TABLE) object;
            personal_table.setMessage("查询成功!");
        }else {
            personal_table.setMessage("错误!请重新操作!");
        }
        return personal_table;
    }

    /**
     * 更新用户个人信息
     * @param u
     * @return
     */
    public PERSONAL_TABLE updateUser(updateUserMessage u){
        PERSONAL_TABLE personal_table = new PERSONAL_TABLE();
        if (personCrudDao.updateUser(u)==1){
            personal_table=personCrudDao.selectUser(u.getID());
            personal_table.setMessage("更新成功!");
        }else {
            personal_table.setMessage("更新失败!");
        }
//        System.out.println(personal_table);
        return personal_table;
    }
}
