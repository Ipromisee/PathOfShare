package edu.whu.entity.users;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;
import edu.whu.entity.User;
import edu.whu.entity.blogs.managerBlog;
import edu.whu.entity.comments.managerComment;
import edu.whu.entity.messages.managerMessage;

public class ManagerUser extends User {
    public ManagerUser(){
        super();
        setType("manager");
    }
    @Override
    public Blog postAndGetBlog(String content) {
        Blog blog = new managerBlog(getId(),content);
        postBlog(blog);
        return blog;
    }

    @Override
    public Comment postAndGetComment(String content, long blogId) {
        Comment comment = new managerComment(getId(),blogId,content);
        postComment(comment);
        return comment;
    }

    @Override
    public Message sendAndGetMessage(String content, long receiverId) {
        Message message = new managerMessage(getId(),receiverId,content);
        sendMessage(message);
        return message;
    }
}
