package edu.whu.entity.comments;

import edu.whu.entity.Comment;

public class managerComment extends Comment {
    public managerComment(Integer posterId, Integer blogId, String content){
        super(posterId, blogId, content);
        setFromWho("manager");
    }
    public managerComment(){
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