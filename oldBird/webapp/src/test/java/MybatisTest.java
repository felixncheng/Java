import com.cheng.Application;
import com.cheng.model.User;
import com.cheng.service.MybatisTestMapper;
import org.junit.Assert;
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
public class MybatisTest {

    @Autowired
    MybatisTestMapper testMapper;

    @Test
    public void query() {
        User query = testMapper.query();
        Assert.assertEquals("cheng",query.getUserName());
    }
}
