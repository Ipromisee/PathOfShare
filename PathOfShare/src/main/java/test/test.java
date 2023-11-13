package test;
import java.sql.*;
//import edu.whu.*;
import edu.whu.service.MySQL.MySqlHelper;

public class test {
    public static void main(String[] args) throws SQLException {
        try{
            ResultSet rs = MySqlHelper.getResultSet("SELECT * FROM users");
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("userId");
                String name = rs.getString("userName");
                String passWord = rs.getString("passWord");

                // 输出数据
                System.out.print("ID: " + id + " Name: " + name + " PassWd: " + passWord);
                System.out.print("\n");
            }
            MySqlHelper.closeResultSet();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
