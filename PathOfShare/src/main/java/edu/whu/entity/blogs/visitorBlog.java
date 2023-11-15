package edu.whu.entity.blogs;

import edu.whu.entity.Blog;

public class visitorBlog extends Blog {
    public visitorBlog(long userId, String content){
        super(userId,content);
        setFromWho("visitor");
    }
    public visitorBlog(){
        super();
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

    @Override
    public int getPriority() {
        return 1;
    }
}
