import java.sql.*;
//import edu.whu.*;
import edu.whu.entity.Blog;
import edu.whu.entity.User;
import edu.whu.entity.users.CommenUser;
import edu.whu.service.MySQL.MySqlHelper;

public class test {
    public static void main(String[] args) throws SQLException {
        //测试查询功能getResultSet和closeResultSet两个函数
        try{
            System.out.println("测试查询功能getResultSet和closeResultSet两个函数");
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
            System.out.println("测试查询功能getResultSet和closeResultSet两个函数，成功\n\n");
        }catch (SQLException se){
            se.printStackTrace();
        }

        //测试数据更新功能,sqlCMD
        try{
            System.out.println("测试数据更新功能,sqlCMD");
            MySqlHelper instance = MySqlHelper.getInstance();

            //测试是否使用占位符两种情况，注意users、blogs等表不需要我们插入id，会自动更新
            String name = "123";
            String passWord = "123456";
            //instance.sqlCMD("INSERT INTO users(userName,passWord) VALUES("+name+","+passWord+")");
            instance.sqlCMD("INSERT INTO users(userName,passWord,type) VALUES(?,?,?)",name,passWord,"user");
            //不需要close函数，资源在sqlCMD命令中自动释放
            //以上两种方法都是可以的
            System.out.println("测试数据更新功能,sqlCMD，成功\n\n");
        }catch (Exception exception){
            exception.printStackTrace();
        }

        //测试获得单例的功能,getInstance
        try{
            System.out.println("测试获得单例的功能,getInstance");
            MySqlHelper instance = MySqlHelper.getInstance();
            User aUser = instance.getInstance(User.class,"SELECT * FROM users WHERE userId = ?",48);
            if(aUser!=null){
                System.out.println("userId："+aUser.getId());
                System.out.println("userName："+aUser.getUserName());
                System.out.println("userPassWord："+aUser.getPassWord());
                //数据库不能保存Boolean（不知道有没有其他方法），改成int类型了
                System.out.println("userGender："+aUser.getGender());
            }
            //该命令资源也已经释放，不需要close函数
            System.out.println("测试获得单例的功能,getInstance，成功\n\n");
        }catch (Exception exception){
            exception.printStackTrace();
        }

        //测试插入并获取ID,insertAndId
        try{
            System.out.println("测试插入并获取ID,insertAndId");
            MySqlHelper instance = MySqlHelper.getInstance();
            String name="insert",passWord="insert";
            int id = instance.insertAndId("INSERT INTO users(userName,passWord,type) VALUES(?,?,?)",name,passWord,"user");
            System.out.println("the Id of insert："+id);
            System.out.println("测试插入并获取ID,insertAndId，成功\n\n");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        //测试抽象工厂方法
        System.out.println("测试抽象工厂方法");
        User aUser ;
        MySqlHelper sqlHelper = MySqlHelper.getInstance();
        aUser = sqlHelper.getInstance(User.class,"SELECT * FROM users WHERE userId = ?",48);
        if(aUser!=null){
            if(aUser.getType().equals("user")){
                System.out.println("this is a common user");
            }
            Blog blog = aUser.postAndGetBlog("111");
            System.out.println("The info of Blog from userA:");
            System.out.println("ID:"+blog.getBlogId());
            System.out.println("Content:"+blog.getContent());
            System.out.println("time:"+blog.getTime());
            System.out.println("userID:"+blog.getUserId());
            System.out.println("fromWHo:"+blog.fromWho());
            System.out.println("displayContent:"+blog.displayContent()+"\n");
        }

        User bUser = sqlHelper.getInstance(User.class,"SELECT * FROM users WHERE userId = ?",49);
        if(bUser!=null){
            if(bUser.getType().equals("manager")){
                System.out.println("this is a manager");
            }
            Blog blog = bUser.postAndGetBlog("222");
            System.out.println("The info of Blog from userB:");
            System.out.println("ID:"+blog.getBlogId());
            System.out.println("Content:"+blog.getContent());
            System.out.println("time:"+blog.getTime());
            System.out.println("userID:"+blog.getUserId());
            System.out.println("fromWHo:"+blog.fromWho());
            System.out.println("displayContent:"+blog.displayContent());
        }
        System.out.println("测试抽象工厂方法，结束\n\n");
    }
}
