package edu.whu.entity.messages;

import edu.whu.entity.Message;
import edu.whu.entity.interfaces.messageInterface;

public class visitorMessage extends Message implements messageInterface {
    public visitorMessage(long senderId, long receiverId, String content){
        super(senderId, receiverId, content);
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
