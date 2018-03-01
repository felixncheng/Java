package com.chengmboy.app.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.chengmboy.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cheng_mboy
 *
 *测试spring框架对各类请求的支持情况
 */
@RestController
@RequestMapping("rest")
public class RestSelfController {

    /**
     * form-data
     * */
    @PostMapping("form_data")
    public String postFormData(@RequestParam("age") short age ,@RequestParam("name") String name ,@RequestParam("info") MultipartFile file) throws IOException {
        System.out.println(age +" "+name+" "+file.getOriginalFilename());
        return "ok";
    }

    /**
     * x-www-form-urlencoded
     * http会进行url编码，spring解码
     * */
    @PostMapping("form_urlencoded")
    public String postFormUrlencoded(@RequestParam("age") short age ,@RequestParam("name") String name) throws IOException {
        System.out.println(age +" "+name+" ");
        return "ok";
    }


    /**
     * application/json
     * requestParam需是编进url，requestBody接收body json部分，可以用来接收对象,spring会自动将json转换为对象
     * */
    @PostMapping("json")
    public String postJson(@RequestParam("age") short age ,@RequestBody Person person) throws IOException {
        System.out.println(age);
        System.out.println(person);
        return "ok";
    }

    /**
     * binary
     * */

    @PostMapping("binary")
    public String postBinary(HttpServletRequest request) throws IOException {
        StreamUtils.copy(request.getInputStream(), System.out);
        return "ok";
    }
    static class Person{
        short age;
        String name;

        public short getAge() {
            return age;
        }

        public void setAge(short age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
