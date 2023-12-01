package edu.whu.entity.comments;

import edu.whu.entity.Comment;

public class userComment extends Comment {
    public userComment(Integer posterId, Integer blogId, String content){
        super(posterId, blogId, content);
        setFromWho("user");
    }
    public userComment(){
        setFromWho("user");
    }
    @Override
    public void show() {
        System.out.println(getContent());
    }

    @Override
    public String displayContent() {
        return getContent();
    }

    @Override
    public String fromWho() {
        return "user";
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
