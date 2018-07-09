package com.chengmboy.test.model.bean;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.chengmboy.test.model.SubUser;
import com.chengmboy.test.model.User;
import org.springframework.beans.BeanUtils;

/**
 * @author cheng_mboy
 */
public class Program {

    private BigDecimal id;

    public static void main(String[] args) {

        Program program = new Program();
        program.setId(new BigDecimal("123456789123456789123456789"));
        String s = JSONObject.toJSONString(program);
        Program program1 = JSONObject.parseObject(s, Program.class);
        System.out.println(program1.getId());
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
}
