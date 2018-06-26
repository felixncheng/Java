package com.cheng.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cheng_mboy
 */
public class Test implements BeanFactoryAware {

    private BeanFactory factory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory =beanFactory;
    }

    public void testAware() {
        Hello hello = (Hello) factory.getBean("hello");
        hello.say();
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new MyClassPathXmlApplicationContext("application.xml");
        Test test = (Test) ctx.getBean("test");
        test.testAware();
    }
}
