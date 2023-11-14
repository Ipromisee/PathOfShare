import java.sql.*;
//import edu.whu.*;
import edu.whu.entity.User;
import edu.whu.service.MySQL.MySqlHelper;

public class test {
    public static void main(String[] args) throws SQLException {
        //测试查询功能getResultSet和closeResultSet两个函数
        try{
            //单例模式，先获得实例
            MySqlHelper instance = MySqlHelper.getInstance();

            //视情况是否使用占位符，防止数据注入问题
            //ResultSet rs = instance.getResultSet("SELECT * FROM users WHERE userId = ?",1);
            ResultSet rs = instance.getResultSet("SELECT * FROM users");
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("userId");
                String name = rs.getString("userName");
                String passWord = rs.getString("passWord");

                // 输出数据
                System.out.print("ID: " + id + " Name: " + name + " PassWd: " + passWord);
                System.out.print("\n");
            }
            instance.closeResultSet();
        }catch (SQLException se){
            se.printStackTrace();
        }
        //测试数据更新功能,sqlCMD
        try{
            MySqlHelper instance = MySqlHelper.getInstance();

            //测试是否使用占位符两种情况，注意users、blogs等表不需要我们插入id，会自动更新
            String name = "123";
            String passWord = "123456";
            //instance.sqlCMD("INSERT INTO users(userName,passWord) VALUES("+name+","+passWord+")");
            instance.sqlCMD("INSERT INTO users(userName,passWord) VALUES(?,?)",name,passWord);
            //不需要close函数，资源在sqlCMD命令中自动释放
            //以上两种方法都是可以的
        }catch (Exception exception){
            exception.printStackTrace();
        }
        //测试获得单例的功能,getInstance
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            User aUser = instance.getInstance(User.class,"SELECT * FROM users WHERE userId = ?",1);
            if(aUser!=null){
                System.out.println("userId："+aUser.getId());
                System.out.println("userName："+aUser.getUserName());
                System.out.println("userPassWord："+aUser.getPassWord());
                //数据库不能保存Boolean（不知道有没有其他方法），改成int类型了
                System.out.println("userGender："+aUser.getGender());
            }
            //该命令资源也已经释放，不需要close函数
        }catch (Exception exception){
            exception.printStackTrace();
        }
        //测试插入并获取ID,insertAndId
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            String name="insert",passWord="insert";
            int id = instance.insertAndId("INSERT INTO users(userName,passWord) VALUES(?,?)",name,passWord);
            System.out.println("the Id of insert："+id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
