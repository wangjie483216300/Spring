package com.CloudDinner.Service;

import com.CloudDinner.Dao.LoginDao;
import com.CloudDinner.Model.Util.LoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServer {
    @Autowired
    private LoginDao loginDao;
    public LoginMessage login(String id,String password){
        LoginMessage loginMessage = new LoginMessage();
        try {
            if (loginDao.selectID(id)==null){
                loginMessage.setMessage("id不存在,请注册!");
            }else {
                if (loginDao.login(id,password)==null){
                    System.out.println(loginDao.login(id,password));
                    loginMessage.setMessage("用户名或密码错误");
                }else {
                    loginMessage.setMessage("登录成功!");
                    loginMessage.setUserId(id);
                    loginMessage.setUserName(loginDao.selectUsername(id));
                    loginMessage.setPassword(password);
                }
            }
        }catch (Exception e){
            loginMessage.setMessage("错误!请重新操作");
            e.printStackTrace();
        }
        return loginMessage;
    }
}
