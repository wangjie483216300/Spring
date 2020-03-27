package com.CloudDinner.Service;

import com.CloudDinner.Dao.RegistDao;
import com.CloudDinner.Model.Util.RegistMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistServer {
    @Autowired
    private RegistDao registDao;

    public RegistMessage regist(String id,String password,String Confirmpassword,String username,String telephone){
        RegistMessage registMessage = new RegistMessage();
        try {
            if (registDao.selectId(id)!=null){
                registMessage.setMessage("用户已存在,请返回登录!");
            }else {
                if (password.equals(Confirmpassword)){
                    registMessage.setID(id);
                    registMessage.setPASSWORD(password);
                    registMessage.setUSER_NAME(username);
                    registMessage.setUSER_TYELEPHONE(telephone);
                    registDao.regist(registMessage);
                    registMessage.setMessage("注册成功!");
                }else {
                    registMessage.setMessage("输入两次密码不相同!");
                }
            }
        } catch (Exception e) {
            registMessage.setMessage("错误!请重新操作!");
            e.printStackTrace();
        }
        return registMessage;

    }
}
