package edu.whu.entity.comments;

import edu.whu.entity.Comment;
import edu.whu.entity.interfaces.commentInterface;

public class managerComment extends Comment implements commentInterface {
    public managerComment(long posterId, long blogId, String content){
        super(posterId, blogId, content);
        setFromWho("manager");
    }
    @Override
    public void show() {
        System.out.println("【管理员】"+getContent());
    }

    @Override
    public String displayContent() {
        return "【管理员】"+getContent();
    }

    @Override
    public String fromWho() {
        return "manager";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}