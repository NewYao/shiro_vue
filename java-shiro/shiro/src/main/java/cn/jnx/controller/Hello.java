package cn.jnx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Hello {
	
    @RequestMapping("/")
    public String a() {
        System.out.println("进入首页");
        return "/index.html";
    }
}
