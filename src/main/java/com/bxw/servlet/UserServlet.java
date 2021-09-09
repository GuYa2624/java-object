package com.bxw.servlet;

import com.bxw.entity.User;
import com.bxw.service.UserService;
import com.bxw.service.impl.UserServiceImpl;
import com.bxw.util.JsonUtil;
import com.bxw.util.PageUtil;
import com.bxw.util.ResultUtil;
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
import java.util.Date;
import java.util.List;

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

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        ResultUtil resultUtil = new ResultUtil(1,"退出成功", null);
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(resultUtil));
        out.close();
    }

    protected void getCountByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page"); // 获取当前页
        int rows = Integer.parseInt(request.getParameter("rows")); // 每页显示的记录数
        int countRows = userService.getRows(); // 获取到数据库中总行数
        PageUtil pageUtil = new PageUtil(page,rows,countRows);
        List<User> userByPage = userService.getUserByPage(pageUtil);
        pageUtil.setList(userByPage);
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(pageUtil));
        out.close();

    }

    // 根据用户名获取用户信息
    protected void getUserByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println(username);
        String page = request.getParameter("page"); // 获取当前页
        int rows = Integer.parseInt(request.getParameter("rows")); // 获取总行数
        int countRows = userService.getCountsByUsername(username);
        PageUtil pageUtil = new PageUtil(page,rows,countRows);
        List<User> userByUsername = userService.getUserByUsername(pageUtil, username);
        pageUtil.setList(userByUsername);
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(pageUtil));
        out.close();

    }

    // 添加用户信息
    protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        int status = Integer.parseInt(request.getParameter("status"));
        int role = Integer.parseInt(request.getParameter("role"));
        int age = Integer.parseInt(request.getParameter("age"));
        int sex = Integer.parseInt(request.getParameter("sex"));
        User user = new User(0,name,phone,age,sex,username,password,null,status,new Date(),role);
        String res = userService.addUser(user);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.close();
    }

    // 删除选中的用户
    protected void delAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        System.out.println(uid);
        String res = userService.delAll(uid);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.close();
    }




}
