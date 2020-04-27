package cn.jnx;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import junit.framework.TestCase;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@SpringBootTest(classes = { ShiroApplication.class }) // 指定启动类，启动整个springboot工程
@RunWith(SpringRunner.class) // 底层用junit SpringJUnit4ClassRunner
@AutoConfigureMockMvc // 测试接口用
class ShiroApplicationTests {

    // @Test
    void contextLoadsold() {
        CacheManager cacheManager = CacheManager.getCacheManager("ehcache");
        Cache cache = cacheManager.getCache("verificationCodeCache");
//        Element ele = new Element("key", "value");  
//        cache.put(ele);  
        Element ele2 = cache.get("key");
        // 把ele放入缓存cache中
    }

    @Before
    public void testBefore() {
        System.out.println("测试前");
    }

    @After
    public void testAfter() {
        System.out.println("测试后");
    }
    
    //下面是测试接口
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void apiTest()throws Exception{
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/valiCode")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//        int status=mvcResult.getResponse().getStatus();
        System.out.println("mvcResult============"+mvcResult.toString());
    }



}
