package cn.jnx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@SpringBootTest
class ShiroApplicationTests {

	@Test
	void contextLoads() {
	    CacheManager cacheManager = CacheManager.getCacheManager("ehcache");
        Cache cache = cacheManager.getCache("verificationCodeCache");  
//        Element ele = new Element("key", "value");  
//        cache.put(ele);  
        Element ele2 = cache.get("key");
        //把ele放入缓存cache中  
	}

}
