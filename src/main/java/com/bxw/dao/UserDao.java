package com.bxw.dao;

import com.bxw.entity.User;
import com.bxw.util.PageUtil;

import java.util.List;

public interface UserDao {
    User login(String username, String password);
    int getCounts();
    List<User> getUserByPage(PageUtil pageUtil);

    int getCountsByUsername(String username);

    List<User> getUserByUsername(PageUtil pageUtil, String username);

    int addUser(User user);

    int delAll(String uid);
}
