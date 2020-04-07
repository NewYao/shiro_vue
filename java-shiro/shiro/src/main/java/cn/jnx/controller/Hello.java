package cn.jnx.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.jnx.bean.ReturnJson;

@Controller
public class Hello {
    @ResponseBody
    @RequestMapping("/login")
    public ReturnJson login() {
        ArrayList<String> aa = new ArrayList<>();
        aa.add("55");
        aa.add("56");
        aa.add("57");
        aa.add("58");
        return new ReturnJson().ok().data(aa);
    }
}
