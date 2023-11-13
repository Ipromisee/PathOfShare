package edu.whu.service;

import edu.whu.entity.Blog;

public interface BlogService {
    public Blog getBlog(long id);
    public Blog addBlog(Blog blog);
    public void delBlog(long id);
    public void updateBlog(Blog blog);
}
