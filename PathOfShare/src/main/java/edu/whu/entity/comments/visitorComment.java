package edu.whu.entity.comments;

import edu.whu.entity.Comment;
import edu.whu.entity.interfaces.commentInterface;

public class visitorComment extends Comment implements commentInterface {
    public visitorComment(long posterId, long blogId, String content){
        super(posterId, blogId, content);
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
