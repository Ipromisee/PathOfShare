package edu.whu.controller;

import edu.whu.entity.User;
import edu.whu.service.MySQL.MySqlHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.ServerEndpoint;

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
    public static User logIn(String userId, String passWord){
        MySqlHelper instance = MySqlHelper.getInstance();
        User newUser = instance.getInstance(User.class,"SELECT * FROM users WHERE userId = ?",userId);
        if(newUser!=null){//存在该用户
            if(passWord.equals(newUser.getPassWord())){//并且密码正确
                setLogInUser(newUser);
                MessageController.successMessage("登录","用户ID为："+newUser.getId(),"用户名为："+newUser.getUserName(),"欢迎使用！");
                return newUser;
            }
            else{//密码错误
                MessageController.errorMessage("登录","密码错误");
                return null;
            }
        }//找不到用户
        else {
            MessageController.errorMessage("登录","找不到该用户","请检查用户名，确认用户是否存在。如果没有账号，请先注册！");
            return null;
        }
    }
    public static void logOut(){
        if(logInUser!=null){
            setLogInUser(null);
            MessageController.successMessage("退出登录");
        }
        else {
            MessageController.errorMessage("退出登录","没有目前正在登录的用户");
        }
    }
}
