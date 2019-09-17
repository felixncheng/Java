package com.chengmboy.app.filter;

import java.io.IOException;
import javax.servlet.*;

/**
 * @author cheng_mboy
 */
public class LogFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("pre doFilter");

        chain.doFilter(request,response);
        System.out.println("post doFilter");
    }

    @Override
    public void destroy() {

        System.out.println("destroy");
    }
}
