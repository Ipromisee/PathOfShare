package edu.whu.entity.users;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;
import edu.whu.entity.User;
import edu.whu.entity.blogs.visitorBlog;
import edu.whu.entity.comments.visitorComment;
import edu.whu.entity.messages.visitorMessage;

public class VisitorUser extends User {
    public VisitorUser(){
        super();
        setType("visitor");
    }
    @Override
    public Blog postAndGetBlog(String content) {
        Blog blog = new visitorBlog(getId(),content);
        postBlog(blog);
        return blog;
    }

    @Override
    public Comment postAndGetComment(String content, long blogId) {
        Comment comment = new visitorComment(getId(),blogId,content);
        postComment(comment);
        return comment;
    }

    @Override
    public Message sendAndGetMessage(String content, long receiverId) {
        Message message = new visitorMessage(getId(),receiverId,content);
        sendMessage(message);
        return message;
    }
}
