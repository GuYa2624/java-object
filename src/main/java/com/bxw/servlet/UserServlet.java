package com.bxw.servlet;

import com.bxw.entity.User;
import com.bxw.service.UserService;
import com.bxw.service.impl.UserServiceImpl;
import com.bxw.util.JsonUtil;
import com.bxw.util.VerifyCodeUtils;
import com.sun.deploy.nativesandbox.comm.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();
    protected void getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession();
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode);
        //生成图片
        int w = 100, h = 30;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String json = userService.login(username, password, code, session);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.close();

    }

    protected void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user_json = (User) session.getAttribute("user");
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(user_json));
        out.close();
    }
}
