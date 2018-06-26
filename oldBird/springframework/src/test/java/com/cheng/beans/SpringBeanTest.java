package com.cheng.beans;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

/**
 * @author cheng_mboy
 */
public class SpringBeanTest {

    @Test
    public void testBean() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("mytestbean.xml"));
        MytestBean myTestBean = (MytestBean) bf.getBean("myTestBean");
        assertEquals("a",myTestBean.getTestStr());
    }
}