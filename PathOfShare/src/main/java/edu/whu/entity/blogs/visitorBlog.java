package edu.whu.entity.blogs;

import edu.whu.entity.Blog;
import edu.whu.entity.interfaces.blogInterface;

public class visitorBlog extends Blog implements blogInterface {
    public visitorBlog(long userId, String content){
        super(userId,content);
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
