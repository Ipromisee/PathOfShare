package edu.whu.entity.blogs;

import edu.whu.entity.Blog;

public class userBlog extends Blog {
    public userBlog(long userId, String content ,String title){
        super(userId,content,title);
        setFromWho("user");
    }
    public userBlog(){
        super();
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

    @Override
    public int getPriority() {
        return 2;
    }
}
