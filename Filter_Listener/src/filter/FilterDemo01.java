package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/hello.jsp")
public class FilterDemo01 implements Filter {
    /**
     * 服务器被关闭后，Filter对象被销毁  正常关闭时只调用一次
     */
    public void destroy() {
        //System.out.println("destroy.....");
    }

    /**
     * 每一次请求被拦截资源时，会执行。执行多次
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("doFilter.....");
        chain.doFilter(req, resp);
    }

    /**
     * 在服务器启动后，会创建Filter对象，然后调用init方法。只执行一次。用于加载资源
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        //System.out.println("init.....");
    }

}
