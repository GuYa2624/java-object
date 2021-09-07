package com.bxw.dao;

import com.bxw.entity.User;

public interface UserDao {
    User login(String username, String password);
}
