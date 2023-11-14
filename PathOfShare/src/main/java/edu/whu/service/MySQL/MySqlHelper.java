package edu.whu.service.MySQL;

import java.sql.*;

public class MySqlHelper {
    static final String url = "jdbc:mysql://localhost:3306/blog";
    static final String username = "root";
    //自己修改密码
    static final String password = "23364464178";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    //单例模式，声明为内存可见性，用于多线程
    private static volatile MySqlHelper instance;
    private MySqlHelper(){
        connection = null;
        statement = null;
        resultSet = null;
    }

    public static MySqlHelper getInstance() {
        //用于多线程，防止线程竞争
        MySqlHelper result = instance;
        if (result != null) {
            return result;
        }
        synchronized(MySqlHelper.class) {
            if (instance == null) {
                instance = new MySqlHelper();
            }
            return instance;
        }
    }

    /**
     * 获得ResultSet；用完后需要调用closeResultSet()释放资源
     * @param sql SQL查询语句，如SELECT * FROM *** WHERE ***
     * @return 对应的ResultSet
     */
    public ResultSet getResultSet(String sql){
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection(url, username, password);
            // 创建Statement对象
            statement = connection.createStatement();
            // 执行SQL语句
            resultSet = statement.executeQuery(sql);
            // 关闭连接和释放资源
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return resultSet;
    }
    /**
     * 释放资源，与getResultSet(String sql)一起使用
     */
    public void closeResultSet() {
        try{
            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
    /**
     * 数据更新操作，不需要返回ResultSet的，输入SQL命令
     * @param cmd SQL命令，如DELETE、UPDATE
     */
    public void sqlCMD(String cmd){
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection(url, username, password);
            // 创建Statement对象
            statement = connection.createStatement();
            // 执行SQL语句
            int rowsAffected = statement.executeUpdate(cmd);

            if (rowsAffected > 0) {
                System.out.println("更新成功，受影响的行数：" + rowsAffected);
            } else {
                System.out.println("没有符合条件的记录");
            }
            // 关闭连接和释放资源
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
