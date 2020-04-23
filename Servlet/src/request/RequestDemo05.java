package request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/requestDemo05")
public class RequestDemo05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求体数据
        //* 请求体：只有POST请求方式，才有请求体，在请求体中封装了POST请求的请求参数
        //获取字符流
        BufferedReader br = request.getReader();
        //读取数据
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
