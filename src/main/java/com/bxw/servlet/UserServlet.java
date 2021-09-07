package com.bxw.servlet;

import com.bxw.util.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
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
}
