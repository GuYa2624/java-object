package com.bxw.service;

import com.bxw.entity.User;
import com.bxw.util.PageUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    String login(String username, String password, String code, HttpSession session);
    int getRows();
    List<User> getUserByPage(PageUtil pageUtil);

    int getCountsByUsername(String username);

    List<User> getUserByUsername(PageUtil pageUtil, String username);

    String addUser(User user);

    String delAll(String uid);
}
