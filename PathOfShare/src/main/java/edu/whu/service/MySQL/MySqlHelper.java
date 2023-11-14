package edu.whu.service.MySQL;

import java.lang.reflect.Field;
import java.sql.*;

public class MySqlHelper {
    static final String url = "jdbc:mysql://localhost:3306/blog";
    static final String username = "root";
    //自己修改密码
    static final String password = "23364464178";

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    //单例模式，声明为内存可见性，用于多线程
    private static volatile MySqlHelper instance;
    private MySqlHelper(){
        connection = null;
        statement = null;
        preparedStatement = null;
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
     * 如：ResultSet rs = instance.getResultSet("SELECT * FROM users WHERE userId = ?",1);
     * 或者：ResultSet rs = instance.getResultSet("SELECT * FROM users");
     * @param sql SQL查询语句，如SELECT * FROM *** WHERE ***；视情况决定是否使用占位符
     * @param args 占位符对象，数量要和问号”？“数量一致
     * @return 对应的ResultSet
     */
    public ResultSet getResultSet(String sql , Object ... args){
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection(url, username, password);
            // 创建Statement对象
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 执行SQL语句
            resultSet = preparedStatement.executeQuery();
            // 因为还要使用ResultSet，在另一个函数中关闭连接和释放资源
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
            //statement.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
    /**
     * 数据更新操作，不需要返回ResultSet的，输入SQL命令；
     * @param cmd SQL命令，如DELETE、UPDATE；视情况决定是否使用占位符
     * @param args 占位符对象，数量要和问号”？“数量一致
     */
    public void sqlCMD(String cmd , Object ... args){
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection(url, username, password);
            // 创建Statement对象
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(cmd);
            //填充占位符
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 执行SQL语句
            //int rowsAffected = statement.executeUpdate(cmd);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("更新成功，受影响的行数：" + rowsAffected);
            } else {
                System.out.println("没有符合条件的记录");
            }
            // 关闭连接和释放资源
            //statement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 执行 插入命令，同时获取最新Id
     * @param cmd SQL命令，如DELETE、UPDATE；视情况决定是否使用占位符
     * @param args 占位符对象，数量要和问号”？“数量一致
     * @return 返回Id数值
     */
    public int insertAndId(String cmd , Object ... args){
        int key = -1;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection(url, username, password);
            // 创建Statement对象
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(cmd,Statement.RETURN_GENERATED_KEYS);
            //填充占位符
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 执行SQL语句
            //int rowsAffected = statement.executeUpdate(cmd);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("更新成功，受影响的行数：" + rowsAffected);
            } else {
                System.out.println("没有符合条件的记录");
            }
            //获取主键值（id）
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }
            // 关闭连接和释放资源
            //statement.close();
            preparedStatement.close();
            connection.close();
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return key;
        }
    }
    /**
     * 释放资源，与sqlCMD(String cmd)一起使用
     */
    /*public void closeCMD(){
        try{
            statement.close();
            connection.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }*/

    /**
     *通过查询语句，返回一个对应类的实例，失败返回null
     *如：String sql = "SELECT user,password FROM user_table WHERE user = ? and password = ?";
     * User returnUser = getInstance(User.class,sql,user,password);
     * @param clazz
     * @param sql sql查询语句，填充数值用？表示，如"SELECT user,password FROM user_table WHERE user = ? and password = ?"
     * @param args ？填充对象，数量和问号数保持一致
     * @return 查询到的实例对象，失败返回null
     * @param <T> 对象类
     */
    public <T> T getInstance(Class<T> clazz,String sql, Object... args) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = resultSet.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if (resultSet.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columValue = resultSet.getObject(i + 1);

                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
}
