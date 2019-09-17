package com.chengmboy.app.controller.common;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chengmboy.app.aop.annotation.Log;

/**
 * @author cheng_mboy
 */
@RestController
@RequestMapping("common")
public class CommonController {

    @GetMapping
    @Log
    public String get() {
        return "Hello World!";
    }

    @GetMapping("path")
    public String getPath() {
        return "path";
    }
}
