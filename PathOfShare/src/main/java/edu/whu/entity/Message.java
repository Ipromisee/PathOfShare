package edu.whu.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "messages")
public abstract class Message {
    private Integer messageId;
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private String fromWho = null;
    private Date time;

    public Message() {
    }

    public Message(Integer senderId, Integer receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.time = new Date();
    }
    public Message(Integer senderId, Integer receiverId, String content, Date time) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.time = time;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
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

    public Integer getMessageId(){ return messageId; }

    public void setMessageId(Integer messageId) { this.messageId = messageId; }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }


    //抽象方法
    /**
     * system.out.println内容+特殊标识
     */
    public abstract void show();

    /**
     * 获得实际输出内容
     * @return 内容+特殊标识
     */
    public abstract String displayContent();

    /**
     * 获得用户类型
     * @return visitor/manager/user
     */
    public abstract String fromWho();
}
