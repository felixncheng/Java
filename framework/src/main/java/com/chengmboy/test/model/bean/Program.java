package com.chengmboy.test.model.bean;

import com.chengmboy.test.model.SubUser;
import com.chengmboy.test.model.User;
import org.springframework.beans.BeanUtils;

/**
 * @author cheng_mboy
 */
public class Program {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setAddr("深圳");
        user.setName("程");
        SubUser subUser = new SubUser();
        for (int i = 0; i < 10; i++) {
            long i1 = System.nanoTime();
            BeanUtils.copyProperties(user, subUser);
            long i2 = System.nanoTime();
            subUser.setId(user.getId());
            subUser.setName(user.getName());
            long i3 = System.nanoTime();
            System.out.println("beanUtils 花费时间: "+ (i2-i1));

            System.out.println("通常花费时间: "+ (i3-i2));
            System.out.println("-------------");
        }



        System.out.println(subUser);
    }
}
