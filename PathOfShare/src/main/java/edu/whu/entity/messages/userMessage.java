package edu.whu.entity.messages;

import edu.whu.entity.Message;
import edu.whu.entity.interfaces.messageInterface;

public class userMessage extends Message implements messageInterface {
    public userMessage(long senderId, long receiverId, String content){
        super(senderId, receiverId, content);
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