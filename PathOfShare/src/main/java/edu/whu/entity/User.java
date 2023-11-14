package edu.whu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.whu.service.MySQL.MySqlHelper;

import java.text.SimpleDateFormat;

import java.util.Date;

@TableName(value = "users")
public class User {
    private static User instance = new User();
    @TableId(type = IdType.AUTO)
    private long userId;
    private String userName;
    private  String passWord;
    private Date birthDay;
    private int gender;
    private String tele;
    private String email;
    private String personalSignature;

    public User() {
    }

    public static User getInstance() {
        return instance;
    }

    public long getId() {
        return userId;
    }

    public void setId(long id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalSignature() {
        return personalSignature;
    }

    public void setPersonalSignature(String personalSignature) {
        this.personalSignature = personalSignature;
    }

    //用户行为

    /**
     * 关注行为
     * @param followId 被关注者ID
     */
    public void follow(long followId){
        MySqlHelper sql = MySqlHelper.getInstance();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sql.sqlCMD("INSERT INTO follows VALUES(?,?,?)",userId,dateFormat.format(date),followId);
    }
    /**
     * 发布博客,同时设置博客id
     * @param blog 博客对象
     */
    public void postBlog(Blog blog){
        MySqlHelper sql = MySqlHelper.getInstance();
        int blogId = sql.insertAndId("INSERT INTO blogs(userId,content) VALUES(?,?)",userId,blog.getContent());
        blog.setBlogId(blogId);
    }
    /**
     *
     */

}
