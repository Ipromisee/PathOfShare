package edu.whu.service.Impl;

import edu.whu.DAO.BlogDao;
import edu.whu.entity.Blog;
import edu.whu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(long id) {
        return null;
    }

    @Override
    public Blog addBlog(Blog blog) {
        return null;
    }

    @Override
    public void delBlog(long id) {

    }

    @Override
    public void updateBlog(Blog blog) {

    }
}
