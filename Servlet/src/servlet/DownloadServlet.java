package servlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求头参数
        String filename = request.getParameter("filename");//在客户端浏览器请求头中得到的文件名

        //使用字节输入流加载文件进内存
        //在服务器中找到 请求头中文件名 对应的 服务器文件路径
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/img/" + filename);// /img/xxx.jpg | xxx.mp4.....
        //使用字节输入流关联
        FileInputStream is = new FileInputStream(realPath); // 字节输入流 将 服务器文件 与 字节输入流 关联

        //设置response 响应头类型
        //获取文件类型
        String mimeType = context.getMimeType(filename);// filename 为 xx.mp4 /xx.jpg / xx.avi .....
        //设置响应头
        //response.setContentType(mimeType);
        response.setHeader("content-type", mimeType);//  image/jpeg
        //设置响应头打开方式:content-disposition
        /**************************************************************/
        //解决中文文件名问题
        //1.获取user-agent请求头、
        //String agent = request.getHeader("user-agent");
        //2.使用工具类方法编码文件名即可
        filename = URLEncoder.encode(filename, "utf-8");
        //System.out.println(filename);
        //filename = DownLoadUtils.getFileName(agent, filename);//URL解码中文文件名  原先中文文件名在URL路径中是%E9式编码 即UTF-8编码
        /**************************************************************/
        response.setHeader("content-disposition", "attachment;filename=" + filename);
        //将输入流的数据写出到字节输出流中(字节输出流一般处理二进制存储的文件 比如视频 图片等)
        ServletOutputStream sos = response.getOutputStream();
        byte[] buffer = new byte[1024 * 8];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {//不到文件末尾
            sos.write(buffer, 0, len);
        }
        is.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
