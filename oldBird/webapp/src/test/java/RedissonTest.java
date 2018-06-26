/*
import com.cheng.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

*/
/**
 * @author cheng_mboy
 *//*

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedissonTest {

    private RedissonClient client;

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void before() {
        client = Redisson.create();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testLiveObject() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/abc").param("id", "1");
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
*/
