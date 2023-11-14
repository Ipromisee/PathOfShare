package edu.whu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;


@TableName(value = "comments")
public abstract class Comment {
    @TableId(type = IdType.AUTO)
    private long commentId;
    private long blogId;
    private String content;
    private int likes;

    private long posterId;
    private String fromWho = null;
    private Date time;

    public Comment() {
    }

    public Comment(long posterId, long blogId, String content) {
        this.posterId = posterId;
        this.blogId = blogId;
        this.content = content;
        this.likes = 0;
        this.time = new Date();
    }
    public Comment(long commentId, long blogId, String content, int likes) {
        this.commentId = commentId;
        this.blogId = blogId;
        this.content = content;
        this.likes = likes;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getTime() {
        return time;
    }

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

    /**
     * 获得权限大小
     * @return 管理者、用户、游客分别是3、2、1
     */
    public abstract int getPriority();
}
