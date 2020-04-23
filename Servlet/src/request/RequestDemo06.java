package request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/requestDemo06")
public class RequestDemo06 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        获取请求参数通用方式：不论get还是post请求方式都可以使用下列方法来获取请求参数
​				1. String getParameter(String name):根据参数名称获取参数值    username=zs&password=123
​				2. String[] getParameterValues(String name):根据参数名称获取参数值的数组  hobby=xx&hobby=game
​				3. Enumeration<String> getParameterNames():获取所有请求的参数名称
​				4. Map<String,String[]> getParameterMap():获取所有参数的map集合
         */
        //String getParameter(String name):根据参数名称获取参数值
        request.setCharacterEncoding("utf-8");//编码方式  否则乱码
        String username = request.getParameter("username");
        System.out.println(username);

        System.out.println("=======================");

        //String[] getParameterValues(String name):根据参数名称获取参数值的数组
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }//只有checked 才算
        System.out.println("=======================");
        //Enumeration<String> getParameterNames():获取所有请求的参数名称
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            System.out.println(s);
        }

        System.out.println("=======================");
        //Map<String,String[]> getParameterMap():获取所有参数的map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        for (String string : strings) {
            String[] strings1 = parameterMap.get(string);
            System.out.println(string);
            for (String s : strings1) {
                System.out.println(s);
            }
            System.out.println("=======================");
        }
    }
}
