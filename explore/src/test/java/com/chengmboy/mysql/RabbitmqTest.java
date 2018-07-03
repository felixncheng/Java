package com.chengmboy.mysql;

import java.util.concurrent.TimeUnit;

import com.chengmboy.Application;
import com.chengmboy.app.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author cheng_mboy
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RabbitmqTest {

    @Autowired
    private Sender sender;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100; i++) {
            sender.send();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
