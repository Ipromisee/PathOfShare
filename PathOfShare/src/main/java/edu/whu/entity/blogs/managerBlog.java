package edu.whu.entity.blogs;

import edu.whu.entity.Blog;

public class managerBlog extends Blog {
    public managerBlog(long userId, String content){
        super(userId,content);
        setFromWho("manager");
    }
    public managerBlog(){
        super();
        setFromWho("manager");
    }
    @Override
    public void show() {
        System.out.println("【管理员】"+getContent());
    }

    @Override
    public String displayContent() {
        return "【管理员】"+getContent();
    }

    @Override
    public String fromWho() {
        return "manager";
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
