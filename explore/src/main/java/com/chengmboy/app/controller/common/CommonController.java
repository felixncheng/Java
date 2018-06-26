package com.chengmboy.app.controller.common;


import com.chengmboy.app.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng_mboy
 */
@RestController
@RequestMapping("common")
@Log
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
