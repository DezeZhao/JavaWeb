package com.login.servlet;

import com.login.dao.UserDao;
import com.login.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/servletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3.封装user对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        /*//获取键值对 map
        Map<String, String[]> map = request.getParameterMap();
        //创建User对象
        User user = new User();
        //封装User对象
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //调用UserDao login方法
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        //判断是否登陆成功
        if (user == null) {

            request.getRequestDispatcher("/servletFail").forward(request, response);

        } else {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/servletSuccess").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
