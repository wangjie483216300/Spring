package JDBC;

import java.io.IOException;
import java.sql.*;

/**
 * 使用JDBC连接数据库需要4步：
 * 1、加载驱动程序；
 * 2、连接数据库；
 * 3、访问数据库；
 * 4、执行查询；要用statement类的executeQuery()方法来下达select指令以查询数据库，
 * executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用。
 * 即语句：ResultSet rs=s.executeQuery(sql);
 */
public class JdbcConnection {
    public static Connection getConnection(){
        Connection connection = null ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/springtest?characterEncoding=UTF-8", "wangjie", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user_info");
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()){
                    String id = resultSet.getString(1);
                    String psw = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    System.out.println("用户id: "+id+"  用户密码: "+psw+"  用户名: "+name);
                }
            }
        } catch (Exception  e) {
            System.out.println("操作数据库失败"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
