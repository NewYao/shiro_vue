package cn.jnx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("cn.jnx.mapper")
@EnableAutoConfiguration
@EnableScheduling // 启动定时函数,刷新秘钥策略
@SpringBootApplication
@EnableTransactionManagement//开启事物
public class ShiroApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(ShiroApplication.class);
    }
}
