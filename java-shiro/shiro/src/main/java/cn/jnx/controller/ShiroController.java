package cn.jnx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jnx.model.ReturnJson;
import cn.jnx.util.rsa.KeyManager;
import cn.jnx.util.rsa.RSAUtil;

@Controller
public class ShiroController {
    /**
     * 无权限访问时
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/unLogin")
    public ReturnJson unLogin() {
        System.out.println("未登录");
        return new ReturnJson().unLogin().message("登录态失效！请重新登录！");
    }

    @RequestMapping("/")
    public String a() {
        System.out.println("进入首页");
        return "/index.html";
    }

    @PostMapping("/login")
    @ResponseBody
    public ReturnJson login(String name, String pass, String type) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, RSAUtil.getTrueStr(pass));
        token.setRememberMe(false);
        try {
            // 登录
            subject.login(token);
        } catch (UnknownAccountException uae) {
            // 用户名未知...
            return new ReturnJson().fail().message("用户不存在！");
        } catch (IncorrectCredentialsException ice) {
            // 凭据不正确，例如密码不正确 ...
            return new ReturnJson().fail().message("密码不正确！");
        } catch (LockedAccountException lae) {
            // 用户被锁定，例如管理员把某个用户禁用...
            return new ReturnJson().fail().message("用户被锁定！");
        } catch (ExcessiveAttemptsException eae) {
            // 尝试认证次数多余系统指定次数 ...
            return new ReturnJson().fail().message("尝试认证次数过多，请稍后重试！");
        } catch (AuthenticationException ae) {
            // 其他未指定异常
            return new ReturnJson().fail().message("未知异常！");
        }
        return new ReturnJson().ok();
    }

    @PostMapping("/logout")
    @ResponseBody
    public ReturnJson logout() {
        System.out.println("退出！");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ReturnJson().ok();
    }
    
    @PostMapping("/pubKey")
    @ResponseBody
    public ReturnJson pubKey() {
        System.out.println("获取公钥!");
        return new ReturnJson().ok().data(KeyManager.getPublic_key());
    }
}
