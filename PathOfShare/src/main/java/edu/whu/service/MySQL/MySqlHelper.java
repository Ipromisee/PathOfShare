package edu.whu.service.MySQL;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;
import edu.whu.entity.User;
import edu.whu.entity.blogs.managerBlog;
import edu.whu.entity.blogs.userBlog;
import edu.whu.entity.blogs.visitorBlog;
import edu.whu.entity.comments.managerComment;
import edu.whu.entity.comments.userComment;
import edu.whu.entity.comments.visitorComment;
import edu.whu.entity.messages.managerMessage;
import edu.whu.entity.messages.userMessage;
import edu.whu.entity.messages.visitorMessage;
import edu.whu.entity.users.CommenUser;
import edu.whu.entity.users.ManagerUser;
import edu.whu.entity.users.VisitorUser;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class MySqlHelper {
    static final String url = "jdbc:mysql://localhost:3306/blog";
    static final String username = "root";
    //自己修改密码
    static final String password = "159463728";

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
    public ResultSet getResultSet(String sql , Object ... args) throws SQLException{
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
        return resultSet;
    }
    /**
     * 释放资源，与getResultSet(String sql)一起使用
     */
    public void closeResultSet() throws SQLException{
        resultSet.close();
        //statement.close();
        preparedStatement.close();
        connection.close();
    }
    /**
     * 数据更新操作，不需要返回ResultSet的，输入SQL命令；
     * @param cmd SQL命令，如DELETE、UPDATE；视情况决定是否使用占位符
     * @param args 占位符对象，数量要和问号”？“数量一致
     */
    public int sqlCMD(String cmd , Object ... args) throws SQLException{
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
        return  rowsAffected;
    }

    /**
     * 执行 插入命令，同时获取最新Id
     * @param cmd SQL命令，如DELETE、UPDATE；视情况决定是否使用占位符
     * @param args 占位符对象，数量要和问号”？“数量一致
     * @return 返回Id数值
     */
    public int insertAndId(String cmd , Object ... args) throws SQLException{
        int key = -1;
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
    }

    /**
     *通过查询语句，返回一个抽象类的实例；自动判断属于哪个子类，并返回该子类；空白字段不赋值，所以会有null属性；失败返回null
     *如：String sql = "SELECT user,password FROM user_table WHERE user = ? and password = ?";
     * User returnUser = getInstance(User.class,sql,user,password);
     * 如果该user的type就是user，则对象是CommonUser
     * @param clazz 抽象类
     * @param sql sql查询语句，填充数值用？表示，如"SELECT user,password FROM user_table WHERE user = ? and password = ?"
     * @param args ？填充对象，数量和问号数保持一致
     * @return 查询到的实例对象，失败返回null
     * @param <T> 对象类
     */
    public <T> T getInstance(Class<T> clazz,String sql, Object... args) throws SQLException , Exception{
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
        Class sonClass = null;
        if (resultSet.next()) {
            if(clazz.equals(User.class)){
                for (int i = 0; i < columnCount; i++) {
                    if(rsmd.getColumnLabel(i + 1).equals("type")){
                        Object columValue = resultSet.getObject(i + 1);
                        switch ((String)columValue ){
                            case "user":
                                sonClass = CommenUser.class;
                                break;
                            case "visitor":
                                sonClass = VisitorUser.class;
                                break;
                            case "manager":
                                sonClass = ManagerUser.class;
                                break;
                            default:
                                throw new Exception("User类型判断错误");
                        }
                    }
                }
            } else if (clazz.equals(Blog.class)) {
                for (int i = 0; i < columnCount; i++) {
                    if(rsmd.getColumnLabel(i + 1).equals("fromWho")){
                        Object columValue = resultSet.getObject(i + 1);
                        switch ((String)columValue ){
                            case "user":
                                sonClass = userBlog.class;
                                break;
                            case "visitor":
                                sonClass = visitorBlog.class;
                                break;
                            case "manager":
                                sonClass = managerBlog.class;
                                break;
                            default:
                                throw new Exception("Blog类型判断错误");
                        }
                    }
                }
            } else if (clazz.equals(Comment.class)) {
                for (int i = 0; i < columnCount; i++) {
                    if(rsmd.getColumnLabel(i + 1).equals("fromWho")){
                        Object columValue = resultSet.getObject(i + 1);
                        switch ((String)columValue ){
                            case "user":
                                sonClass = userComment.class;
                                break;
                            case "visitor":
                                sonClass = visitorComment.class;
                                break;
                            case "manager":
                                sonClass = managerComment.class;
                                break;
                            default:
                                throw new Exception("Comment类型判断错误");
                        }
                    }
                }
            } else if (clazz.equals(Message.class)) {
                for (int i = 0; i < columnCount; i++) {
                    if (rsmd.getColumnLabel(i + 1).equals("fromWho")) {
                        Object columValue = resultSet.getObject(i + 1);
                        switch ((String) columValue) {
                            case "user":
                                sonClass = userMessage.class;
                                break;
                            case "visitor":
                                sonClass = visitorMessage.class;
                                break;
                            case "manager":
                                sonClass = managerMessage.class;
                                break;
                            default:
                                throw new Exception("Message类型判断错误");
                        }
                    }
                }
            }
            T t = (T) sonClass.newInstance();
            // 处理结果集一行数据中的每一个列
            for (int i = 0; i < columnCount; i++) {
                // 获取列值
                Object columValue = resultSet.getObject(i + 1);
                //如果非空则赋值
                if(columValue != null){
                    // 获取列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    // 处理sql中datatime 和java中Date类型不匹配问题
                    if (columValue.getClass().toString().equals("class java.time.LocalDateTime")){
                        //columValue = Timestamp.valueOf((LocalDateTime) columValue);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDateTime = formatter.format(Timestamp.valueOf((LocalDateTime) columValue));
                        columValue = formattedDateTime;
                    }
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return t;
        }
        return null;
    }
}
