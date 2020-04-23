package com.login.servlet;

import com.login.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletSuccess")
public class ServletSuccess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("登录成功");
        User user = (User) request.getAttribute("user");
//        if (user != null) {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("恭喜您！" + user.getUsername() + "登录成功！");
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
