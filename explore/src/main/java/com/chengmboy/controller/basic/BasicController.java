package com.chengmboy.controller.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实践转发与重定向的区别。
 * 转发浏览器地址栏不变,转发后会执行下面代码。
 * 重定向浏览器地址栏会变，重定向后会执行后面代码。
 * 所有的return没有意义。
 *
 * @author cheng_mboy
 */
@RestController
@RequestMapping("basic")
public class BasicController {

    @GetMapping
    @RequestMapping("dispatch")
    public String getDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/basic").forward(request, response);
        System.out.println("转发后");
        return "已转发";
    }

    @GetMapping
    public String receive(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @GetMapping("redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/basic");
        System.out.println("重定向后");
        return "已重定向";
    }
}
