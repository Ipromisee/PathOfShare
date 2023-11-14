package edu.whu.entity.interfaces;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.Message;

public interface userInterface {

    /**
     * 根据content发布blog，同时返回Blog
     * @param content 内容字符串
     * @return 设置好fromWho和id的blog
     */
    public Blog postAndGetBlog(String content);
    /**
     * 根据content发布comment，同时返回Comment
     * @param content 内容字符串
     * @return 设置好fromWho和id的comment
     */
    public Comment postAndGetComment(String content,long blogId);
    /**
     * 根据content发送message，同时返回Message
     * @param content 内容字符串
     * @return 设置好fromWho和id的Message
     */
    public Message sendAndGetMessage(String content,long receiverId);
}
