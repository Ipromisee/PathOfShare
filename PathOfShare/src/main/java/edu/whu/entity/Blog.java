package edu.whu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "blog")
public class Blog {
    @TableId(type = IdType.AUTO)
    private long blogId;
    private long userId;
    private String content;
    private int visit;
    private int like;

    public Blog() {
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
}
