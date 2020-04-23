package com.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");

        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        boolean flag = false;//没有cookie
        //遍历
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("lastTime".equals(name)) {
                    flag = true;
                    //有此cookie 不是第一次访问
                    //获取cookie中的value
                    String value = cookie.getValue();
                    value = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("<h1>欢迎回来，您上次访问的时间是:" + value + "</h1>");

                    //把当前时间继续存入cookie  便于下一次访问

                    //设置cookie的value
                    //获取当前时间的字符串，重新设置Cookie的值，重新发送cookie
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formatDate = sdf.format(date);
                    //System.out.println("URL编码前：" + formatDate);
                    formatDate = URLEncoder.encode(formatDate, "utf-8");
                    //System.out.println("URL编码后：" + formatDate);
                    //设置cookie value值 存储当前日期
                    cookie.setValue(formatDate);

                    //设置cookie的存活时间
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    //cookie.setMaxAge(0);
                    //响应头中添加cookie
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        if (cookies == null || cookies.length == 0 || !flag) {
            //没有cookie 第一次访问
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatDate = sdf.format(date);
            response.getWriter().write("<h1>您好!您首次访问的时间为:" + formatDate + "</h1>");
            formatDate = URLEncoder.encode(formatDate, "utf-8");//日期格式中有空格特殊字符 需要URL编码才行 不然报错
            //把当前时间加入cookie
            Cookie cookie = new Cookie("lastTime", formatDate);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            //cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
