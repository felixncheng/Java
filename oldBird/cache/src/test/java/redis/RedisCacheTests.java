package redis;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author cheng_mboy
 * @create 2017-06-04-13:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisCacheTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Before()
    public void before() {
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
    }
    @Test
    public void test() {
        /*User u1 = userRepository.findByName("AAA");
        System.out.println("第一次查询：" + u1.getAge());
        User u2 = userRepository.findByName("AAA");
        System.out.println("第二次查询：" + u2.getAge());
        u1.setAge(20);
        userRepository.save(u1);
        User u3 = userRepository.findByName("AAA");
        System.out.println("第三次查询：" + u3.getAge());*/

        Long[] ids = userRepository.findIds();
    }

}
