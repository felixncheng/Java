package com.chengmboy.orm;

import java.util.ArrayList;
import java.util.Date;
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
        /*String url = "jdbc:mysql://localhost:3306/train?useUnicode=true&characterEncoding=utf-8&&useSSL=false";
        String username = "root";
        String password = "123456";
        String driver = "com.mysql.jdbc.Driver";*/

        String url = "jdbc:mysql://192.168.0.3:3306/coin_sub?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String username = "digi";
        String password = "digi123456";
        String driver = "com.mysql.jdbc.Driver";

        url = "jdbc:mysql://localhost:3306/advice?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        username = "root";
        password = "123456";
        driver = "com.mysql.jdbc.Driver";
        DataBase dataBase = new DataBase(url, username, password, driver);
        dataBase.deleteAll("delete from dock_market_data");
        List<Object[]> list = new ArrayList<>();
        dataBase.deleteAll("ALTER TABLE dock_market_data AUTO_INCREMENT=1");
        int sum = 2800;
       for (int i = sum; i > 0; i--) {
            list.add(new Object[]{i%127, 2 * i, (3 * i) % 5, 4, 5,
                    6, 7, 8, 9, 10,
                    11});
        }
        long start = System.currentTimeMillis();
       dataBase.insertList("insert into dock_market_data(market_from,symbol,type,open,high,low,close,volume,volume_money,start_id,end_id)" +
                " values (?,?,?,?,?," +
                "?,?,?,?,?," +
                "?)", list);
        System.out.println("批量插入" + sum + "条,花费时间（毫秒） :" + (System.currentTimeMillis() - start));


        dataBase.deleteAll("delete from dock_market_data");
        dataBase.deleteAll("ALTER TABLE dock_market_data AUTO_INCREMENT=1");
        start = System.currentTimeMillis();
        for (Object[] objects : list) {
            dataBase.insert("insert into dock_market_data(market_from,symbol,type,open,high,low,close,volume,volume_money,start_id,end_id)" +
                    " values (?,?,?,?,?," +
                    "?,?,?,?,?," +
                    "?)", objects);
        }
        System.out.println("单条批量插入" + sum + "条,花费时间（毫秒） :" + (System.currentTimeMillis() - start));
    }
}
