package edu.whu.entity.messages;

import edu.whu.entity.Message;

public class userMessage extends Message {
    public userMessage(Integer senderId, Integer receiverId, String content){
        super(senderId, receiverId, content);
        setFromWho("user");
    }
    public userMessage(){
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
}
