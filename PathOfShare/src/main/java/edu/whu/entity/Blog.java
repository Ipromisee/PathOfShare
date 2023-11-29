package edu.whu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "blog")
public abstract class Blog {
    @TableId(type = IdType.AUTO)
    private long blogId;
    private long userId;
    private String content;
    private int visit;
    private int like;

    private String fromWho = null;
    private Date time;

    private String title;

    public Blog() {
    }

    public Blog(long userId, String content ,String title) {
        this.userId = userId;
        this.content = content;
        this.visit = 0;
        this.like = 0;
        this.time = new Date();
        this.title = title;
    }
    public Blog(long blogId, long userId, String content, int visit, int like) {
        this.blogId = blogId;
        this.userId = userId;
        this.content = content;
        this.visit = visit;
        this.like = like;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
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

    public void setTitle(String title) { this.title = title; }

    public String getTitle() { return title; }

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
