package edu.whu.entity.messages;

import edu.whu.entity.Message;

public class managerMessage extends Message {
    public managerMessage(long senderId, long receiverId, String content){
        super(senderId, receiverId, content);
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