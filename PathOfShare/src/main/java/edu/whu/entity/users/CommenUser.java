package edu.whu.entity.users;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;
import edu.whu.entity.User;
import edu.whu.entity.blogs.userBlog;
import edu.whu.entity.comments.userComment;
import edu.whu.entity.interfaces.userInterface;
import edu.whu.entity.messages.userMessage;

public class CommenUser extends User implements userInterface {
    public CommenUser(){
        super();
        setType("user");
    }
    @Override
    public Blog postAndGetBlog(String content) {
        Blog blog = new userBlog(getId(),content);
        postBlog(blog);
        return blog;
    }

    @Override
    public Comment postAndGetComment(String content, long blogId) {
        Comment comment = new userComment(getId(),blogId,content);
        postComment(comment);
        return comment;
    }

    @Override
    public Message sendAndGetMessage(String content, long receiverId) {
        Message message = new userMessage(getId(),receiverId,content);
        sendMessage(message);
        return message;
    }

}
