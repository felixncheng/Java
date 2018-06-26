package com.cheng.beans;

import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cheng_mboy
 */
public class LoopTest {

    @Test(expected = BeanCurrentlyInCreationException.class)
    public void testCircleByConstructor() throws Throwable {
        try {
            new ClassPathXmlApplicationContext("loop.xml");
        } catch (Exception e) {
            Throwable el = e.getCause().getCause().getCause();
            throw el;
        }
    }
}
