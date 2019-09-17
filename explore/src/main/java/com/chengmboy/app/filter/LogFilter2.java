package com.chengmboy.app.filter;

import java.io.IOException;
import javax.servlet.*;

/**
 * @author cheng_mboy
 */
public class LogFilter2 implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init2");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("pre doFilter2");

        chain.doFilter(request,response);
        System.out.println("post doFilter2");
    }

    @Override
    public void destroy() {

        System.out.println("destroy2");
    }
}
