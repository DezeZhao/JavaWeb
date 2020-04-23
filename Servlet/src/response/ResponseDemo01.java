package response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/responseDemo01")
public class ResponseDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo01......");

        //访问/responseDemo1，会自动跳转到/responseDemo2资源
        //状态码为302 代表重定向
        response.setStatus(302);
        /*//2.设置响应头location   静态设置虚拟目录和状态码
        response.setHeader("location", "/Servlet_war_exploded/responseDemo02");

        request.setAttribute("msg", "helloworld");//request域存储数据*/

        //动态获取虚拟目录
        String contextPath = request.getContextPath();

        response.sendRedirect(contextPath + "/responseDemo02");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
