package edu.whu.entity.messages;

import edu.whu.entity.Message;

public class managerMessage extends Message {
    public managerMessage(Integer senderId, Integer receiverId, String content){
        super(senderId, receiverId, content);
        setFromWho("manager");
    }
    public managerMessage(){
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
}