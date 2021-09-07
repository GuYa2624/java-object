package com.bxw.service;

import javax.servlet.http.HttpSession;

public interface UserService {
    String login(String username, String password, String code, HttpSession session);

}
