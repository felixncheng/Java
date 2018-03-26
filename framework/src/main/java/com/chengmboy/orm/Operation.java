package com.chengmboy.orm;

import java.util.List;
import java.util.Map;

import com.chengmboy.datasource.DataBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author cheng_mboy
 */
public class Operation {

    public static void main(String[] args) throws JsonProcessingException {
        String url = "jdbc:mysql://localhost:3306/train?useUnicode=true&characterEncoding=utf-8&&useSSL=false";
        String username = "root";
        String password = "123456";
        String driver = "com.mysql.jdbc.Driver";
        DataBase dataBase = new DataBase(url, username, password, driver);
        List<Map<String, Object>> select = dataBase.select("select * from station");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(select);
        System.out.println(s);
    }
}
