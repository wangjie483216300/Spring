package com.myBatis.Test;


import com.myBatis.Dao.UserDao;
import com.myBatis.Model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class test {
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatisconfig.xml");
        //创建sqlsessionfactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //使用工厂生产sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //使用sqlsession创建Dao接口的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
        in.close();
    }
}
