package edu.whu.entity.users;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;
import edu.whu.entity.User;
import edu.whu.entity.blogs.userBlog;
import edu.whu.entity.comments.userComment;
import edu.whu.entity.messages.userMessage;

import java.sql.SQLException;

public class CommenUser extends User {
    public CommenUser(){
        super();
        setType("user");
    }
    @Override
    public Blog postAndGetBlog(String content ,String title) throws SQLException {
        Blog blog = new userBlog(getId(),content,title);
        postBlog(blog);
        return blog;
    }

    @Override
    public Comment postAndGetComment(String content, Integer blogId) throws SQLException  {
        Comment comment = new userComment(getId(),blogId,content);
        postComment(comment);
        return comment;
    }

    @Override
    public Message sendAndGetMessage(String content, Integer receiverId) throws SQLException  {
        Message message = new userMessage(getId(),receiverId,content);
        sendMessage(message);
        return message;
    }

}
