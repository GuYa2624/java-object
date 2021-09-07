package com.bxw.service.impl;

import com.bxw.dao.UserDao;
import com.bxw.dao.impl.UserDaoImpl;
import com.bxw.entity.User;
import com.bxw.service.UserService;
import com.bxw.util.JsonUtil;
import com.bxw.util.ResultUtil;

import javax.servlet.http.HttpSession;

/**
 * 用于判断登录的用户的账户密码，以及验证码
 *
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public String login(String username, String password, String code, HttpSession session) {
        ResultUtil resultUtil = null;
        String verCode = (String) session.getAttribute("verCode");
        // 1.首先判断验证码是否正确，如果正确接着判断用户密码和密码，否则提示验证码错误。
        if (code.equalsIgnoreCase(verCode)){
            // 2.验证码判断正确，则进行用户名密码验证，如果正确，进行用户状态判断，错误则提示。
            User user = userDao.login(username,password);
            if (user != null){
                // 3.判断状态码，确认用户是否是激活的状态
                if (user.getStatus() == 1){
                    // 4.判断权限，确认用户是否有访问权限
                    if (user.getRole() == 1){
                        session.setAttribute("user",user);
                        // 登录成功
                        resultUtil = new ResultUtil(1,"登陆成功",null);

                    }else {
                        // 权限不够
                        resultUtil = new ResultUtil(2,"权限不足，无法访问",null);
                    }

                }else {
                    // 账号被禁用
                    resultUtil = new ResultUtil(3,"账号被禁用",null);
                }

            }else {
                // 用户名或密码不正确
                resultUtil = new ResultUtil(4,"用户名或密码不正确",null);
            }
        }else {
            // 验证码不正确
            resultUtil = new ResultUtil(5, "验证码不正确", null);
        }
        // 直接返回一个json字符串
        return JsonUtil.toJson(resultUtil);
    }
}
