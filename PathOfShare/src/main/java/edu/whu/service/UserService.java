package edu.whu.service;

import edu.whu.entity.User;

public interface UserService {
    public User getUser(long id);
    public User addUser(User user);

    public void delUser(long id);
    public void updateUser(User user);
}
