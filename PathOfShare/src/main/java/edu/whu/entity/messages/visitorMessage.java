package edu.whu.entity.messages;

import edu.whu.entity.Message;

public class visitorMessage extends Message {
    public visitorMessage(Integer senderId, Integer receiverId, String content){
        super(senderId, receiverId, content);
        setFromWho("visitor");
    }
    public visitorMessage(){
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
}
