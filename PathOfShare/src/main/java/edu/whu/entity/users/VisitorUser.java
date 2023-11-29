package edu.whu.entity.users;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;
import edu.whu.entity.User;
import edu.whu.entity.blogs.userBlog;
import edu.whu.entity.blogs.visitorBlog;
import edu.whu.entity.comments.visitorComment;
import edu.whu.entity.messages.visitorMessage;

import java.sql.SQLException;

public class VisitorUser extends User {
    public VisitorUser(){
        super();
        setType("visitor");
    }
    @Override
    public Blog postAndGetBlog(String content ,String title) throws SQLException {
        Blog blog = new userBlog(getId(),content,title);
        postBlog(blog);
        return blog;
    }

    @Override
    public Comment postAndGetComment(String content, long blogId) throws SQLException  {
        Comment comment = new visitorComment(getId(),blogId,content);
        postComment(comment);
        return comment;
    }

    @Override
    public Message sendAndGetMessage(String content, long receiverId) throws SQLException  {
        Message message = new visitorMessage(getId(),receiverId,content);
        sendMessage(message);
        return message;
    }
}
