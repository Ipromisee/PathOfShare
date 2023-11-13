package edu.whu.service.Impl;

import edu.whu.DAO.UserDao;
import edu.whu.entity.User;
import edu.whu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void delUser(long id) {

    }

    @Override
    public void updateUser(User user) {

    }
}
