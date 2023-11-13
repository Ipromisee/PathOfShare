package edu.whu.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "messages")
public class Message {
    private long senderId;
    private long receiverId;
    private String content;
    private Date time;

    public Message() {
    }

    public Message(long senderId, long receiverId, String content, Date time) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.time = time;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
