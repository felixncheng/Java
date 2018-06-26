package com.chengmboy.app.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chengmboy.app.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author cheng_mboy
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.hasMethodAnnotation(Log.class) || method.getBeanType().isAnnotationPresent(Log.class)) {
                LOGGER.info("enter time {}", System.currentTimeMillis());
            }
        }
        return super.preHandle(request, response, handler);
    }
}
