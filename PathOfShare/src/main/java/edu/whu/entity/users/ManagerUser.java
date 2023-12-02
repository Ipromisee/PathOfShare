package edu.whu.entity.users;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;
import edu.whu.entity.User;
import edu.whu.entity.blogs.managerBlog;
import edu.whu.entity.blogs.userBlog;
import edu.whu.entity.comments.managerComment;
import edu.whu.entity.messages.managerMessage;

import java.sql.SQLException;

public class ManagerUser extends User {
    public ManagerUser(){
        super();
        setType("manager");
    }
    @Override
    public Blog postAndGetBlog(String content ,String title) throws SQLException {
        Blog blog = new managerBlog(getId(),content,title);
        postBlog(blog);
        return blog;
    }

    @Override
    public Comment postAndGetComment(String content, Integer blogId) throws SQLException  {
        Comment comment = new managerComment(getId(),blogId,content);
        postComment(comment);
        return comment;
    }

    @Override
    public Message sendAndGetMessage(String content, Integer receiverId) throws SQLException  {
        Message message = new managerMessage(getId(),receiverId,content);
        sendMessage(message);
        return message;
    }
}
