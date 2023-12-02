package edu.whu.controller;

import edu.whu.entity.Blog;
import edu.whu.entity.User;
import edu.whu.service.MySQL.MySqlHelper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.ServerEndpoint;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private static User logInUser = null;

    /**
     * 设置私有，只有登录和登出才能设置
     * @param user 用户
     */
    static void setLogInUser(User user){
        logInUser = user;
    }

    /**
     * 返回目前正在登录的用户
     * @return 用户实例
     */
    public static User getLogInUser() {
        if(logInUser != null){
            return logInUser;
        }
        else {
            MessageController.errorMessage("获取用户信息","没有已经登录的用户！");
            return null;
        }
    }

    /**
     * 登陆，登陆成功返回用户实例
     * @param userId id
     * @param passWord 密码
     * @return 用户实例
     */
    @GetMapping("/login")
    public ResponseEntity<Map<String , String>> logIn(Integer userId, String passWord) {
        Map<String , String> result = new HashMap<>();
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            User newUser = instance.getInstance(User.class,"SELECT * FROM users WHERE userId = ?",userId);
            if(newUser!=null){//存在该用户
                if(passWord.equals(newUser.getPassWord())){//并且密码正确
                    setLogInUser(newUser);
                    fillMapResult(result,newUser);
                    MessageController.successMessage("登录","用户ID为："+newUser.getId(),"用户名为："+newUser.getUserName(),"欢迎使用！");
                    return ResponseEntity.ok(result);
                }
                else{//密码错误
                    result.put("error","密码错误");
                    MessageController.errorMessage("登录","密码错误");
                    return ResponseEntity.badRequest().body(result);
                }
            }//找不到用户
            else {
                result.put("error","找不到用户");
                MessageController.errorMessage("登录","找不到该用户","请检查用户名，确认用户是否存在。如果没有账号，请先注册！");
                return ResponseEntity.badRequest().body(result);
            }
        }catch (Exception e){
            result.put("error","数据库错误");
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logOut(){
        if(logInUser!=null){
            setLogInUser(null);
            MessageController.successMessage("退出登录");
            return ResponseEntity.ok().build();
        }
        else {
            MessageController.errorMessage("退出登录","没有目前正在登录的用户");
            return ResponseEntity.badRequest().build();
        }
    }

    private void fillMapResult(Map<String,String> result,User user){
        result.put("userId",String.valueOf(user.getId()));
        result.put("userName", user.getUserName());
        result.put("type", user.getType());
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<Map<String , String>> getUserInfo(int userId){
        Map<String , String> result = new HashMap<>();
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            User newUser = instance.getInstance(User.class,"SELECT * FROM users WHERE userId = ?",userId);
            if(newUser!=null){//存在该用户
                fillMapResult(result,newUser);
                return ResponseEntity.ok(result);
            }//找不到用户
            else {
                result.put("error","找不到用户");
                MessageController.errorMessage("登录","找不到该用户","请检查用户名，确认用户是否存在。如果没有账号，请先注册！");
                return ResponseEntity.badRequest().body(result);
            }
        }catch (Exception e){
            result.put("error","数据库错误");
            return ResponseEntity.badRequest().body(result);
        }
    }
    @PutMapping("/likeBlog")
    public ResponseEntity<Map<String , String>> likeBlog(int blogId){
        Map<String , String> result = new HashMap<>();
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            Blog blog = instance.getInstance(Blog.class,"SELECT * FROM blogs WHERE blogId = ?",blogId);
            UserController.getLogInUser().likeBlog(blog);
            result.put("success","点赞成功");
            result.put("likes",String.valueOf(blog.getLike()));
            return ResponseEntity.ok(result);
        }catch (Exception e){
            result.put("error","数据库错误");
            return ResponseEntity.badRequest().body(result);
        }
    }
}
