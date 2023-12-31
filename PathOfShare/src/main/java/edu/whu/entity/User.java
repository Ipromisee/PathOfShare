package edu.whu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.whu.service.MySQL.MySqlHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Date;

@TableName(value = "users")
public abstract class User {
    //private static User instance = new User();
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private  String passWord;
    private Date birthDay;
    private int gender;
    private String tele;
    private String email;
    private String personalSignature;

    private String type = null;
    private Blog readingBlog = null;
    public User() {
    }

    /*public static User getInstance() {
        return instance;
    }*/

    public Integer getId() {
        return userId;
    }

    public void setId(Integer id) {
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

    public Blog getReadingBlog(){
        return readingBlog;
    }
    public void setReadingBlog(Blog rBlog){
        this.readingBlog = rBlog;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    //用户行为

    /**
     * 关注行为
     * @param followId 被关注者ID
     */
    public void follow(Integer followId) throws SQLException {
        MySqlHelper sql = MySqlHelper.getInstance();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sql.sqlCMD("INSERT INTO follows VALUES(?,?,?)",userId,dateFormat.format(date),followId);
    }
    /**
     * 发布博客,同时设置博客id
     * @param blog 博客对象
     */
    public void postBlog(Blog blog) throws SQLException {
        MySqlHelper sql = MySqlHelper.getInstance();
        int blogId = sql.insertAndId("INSERT INTO blogs(userId,content,time,fromWho,title) VALUES(?,?,?,?,?)",userId,blog.getContent(),new Date(),blog.getFromWho(),blog.getTitle());
        blog.setBlogId(blogId);
    }
    /**
     * 删除博客
     * @param blog 要删除的博客，需要先确定blog具有id
     */
    public void deleteBlog(Blog blog) throws SQLException {
        if(blog.getBlogId()==0){
            System.out.println("该blog还没有ID");
        }
        else{
            MySqlHelper sqlHelper = MySqlHelper.getInstance();
            sqlHelper.sqlCMD("DELETE FROM blogs WHERE blogId = ?",blog.getBlogId());
        }
    }

    /**
     * 修改博客
     * @param blog 需要先确定blog具有id
     */
    public void updateBlog(Blog blog) throws SQLException {
        if(blog.getBlogId()==0){
            System.out.println("该blog还没有ID");
        }
        else{
            MySqlHelper sqlHelper = MySqlHelper.getInstance();
            sqlHelper.sqlCMD("UPDATE blogs SET content = ? WHERE blogId = ?",blog.getContent(),blog.getBlogId());
        }
    }
    /**
     * 点赞
     * @param blog 需要先确定blog具有id
     */
    public void likeBlog(Blog blog) throws SQLException {
        if(blog.getBlogId()==0){
            System.out.println("该blog还没有ID");
        }
        else{
            blog.setLike(blog.getLike()+1);
            MySqlHelper sqlHelper = MySqlHelper.getInstance();
            sqlHelper.sqlCMD("UPDATE blogs SET likes = ? WHERE blogId = ?",blog.getLike(),blog.getBlogId());
        }
    }

    /**
     * 访问blog，visit+1同时改变readingBlog
     * @param blog 需要先确定blog具有id
     */
    public void visitBlog(Blog blog) throws SQLException {
        if(blog.getBlogId()==0){
            System.out.println("该blog还没有ID");
        }
        else{
            blog.setVisit(blog.getVisit()+1);
            MySqlHelper sqlHelper = MySqlHelper.getInstance();
            sqlHelper.sqlCMD("UPDATE blogs SET visit = ? WHERE blogId = ?",blog.getVisit(),blog.getBlogId());
            readingBlog = blog;
        }
    }

    /**
     * 退出博客
     */
    public void quitBlog(){
        if(readingBlog!=null){
            readingBlog = null;
            System.out.println("成功退出该博客");
        }
    }
    /**
     * 发布评论,同时设置评论id
     * 需要具有readingBlog，否则出错
     * @param comment 评论对象
     */
    public void postComment(Comment comment) throws SQLException {
        if(readingBlog == null){
            System.out.println("发布评论出错：当前没有查看博客！");
        }
        else{
            MySqlHelper sql = MySqlHelper.getInstance();
            int commentId = sql.insertAndId("INSERT INTO comments(blogId,posterId,content,fromWho,time) VALUES(?,?,?,?,?)",readingBlog.getBlogId(),userId,comment.getContent(),comment.getFromWho(),new Date());
            comment.setCommentId(commentId);
        }
    }
    /**
     * 删除评论
     * @param comment 要删除的评论，需要先确定comment具有id
     */
    public void deleteComment(Comment comment) throws SQLException {
        if(comment.getCommentId()==0){
            System.out.println("该comment还没有ID");
        }
        else{
            MySqlHelper sqlHelper = MySqlHelper.getInstance();
            sqlHelper.sqlCMD("DELETE FROM comments WHERE commentId = ?",comment.getCommentId());
        }
    }

    /**
     * 修改评论
     * @param comment 需要先确定comment具有id
     */
    public void updateComment(Comment comment) throws SQLException {
        if(comment.getCommentId()==0){
            System.out.println("该comment还没有ID");
        }
        else{
            MySqlHelper sqlHelper = MySqlHelper.getInstance();
            sqlHelper.sqlCMD("UPDATE comments SET content = ? WHERE commentId = ?",comment.getContent(),comment.getCommentId());
        }
    }
    /**
     * 点赞评论
     * @param comment 需要先确定comment具有id
     */
    public void likecComment(Comment comment) throws SQLException {
        if(comment.getCommentId()==0){
            System.out.println("该comment还没有ID");
        }
        else{
            comment.setLikes(comment.getLikes()+1);
            MySqlHelper sqlHelper = MySqlHelper.getInstance();
            sqlHelper.sqlCMD("UPDATE comments SET like = ? WHERE commentId = ?",comment.getLikes(),comment.getCommentId());
        }
    }

    /**
     * 发送私信
     * @param message 私信对象
     */
    public void sendMessage(Message message) throws SQLException {
        MySqlHelper instance = MySqlHelper.getInstance();
        int messageId = instance.insertAndId("INSERT INTO messages(senderId,receiverId,time,content,fromWho) VALUES(?,?,?,?,?)",userId,message.getReceiverId(),message.getTime(),message.getContent(),message.getFromWho());
        message.setMessageId(messageId);
    }


    //抽象方法
    /**
     * 根据content发布blog，同时返回Blog
     * @param content 内容字符串
     * @return 设置好fromWho和id的blog
     */
    public abstract Blog postAndGetBlog(String content,String title) throws SQLException;
    /**
     * 根据content发布comment，同时返回Comment
     * @param content 内容字符串
     * @return 设置好fromWho和id的comment
     */
    public abstract Comment postAndGetComment(String content,Integer blogId) throws SQLException ;
    /**
     * 根据content发送message，同时返回Message
     * @param content 内容字符串
     * @return 设置好fromWho和id的Message
     */
    public abstract Message sendAndGetMessage(String content,Integer receiverId) throws SQLException ;
}
