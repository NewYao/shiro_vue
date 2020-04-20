package cn.jnx.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig  {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                          .allowedOrigins("*")  
                          .allowedHeaders("Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild")
                          .allowCredentials(true)
                          .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                          .maxAge(3600);  
            }
        };
    }

}