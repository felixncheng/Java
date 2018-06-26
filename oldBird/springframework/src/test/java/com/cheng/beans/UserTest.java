package com.cheng.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author cheng_mboy
 */
public class UserTest {

    @Test
    public void showMe() throws Exception {
        ApplicationContext bf = new ClassPathXmlApplicationContext("user.xml");
        GetBeanTest test = (GetBeanTest) bf.getBean("getBeanTest");
        test.showMe();
    }

}