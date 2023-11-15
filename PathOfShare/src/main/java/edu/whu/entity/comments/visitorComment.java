package edu.whu.entity.comments;

import edu.whu.entity.Comment;

public class visitorComment extends Comment {
    public visitorComment(long posterId, long blogId, String content){
        super(posterId, blogId, content);
        setFromWho("visitor");
    }
    public visitorComment(){
        setFromWho("visitor");
    }
    @Override
    public void show() {
        System.out.println("【游客】"+getContent());
    }

    @Override
    public String displayContent() {
        return "【游客】"+getContent();
    }

    @Override
    public String fromWho() {
        return "visitor";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
