package cn.jnx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jnx.model.ReturnJson;

@RestController
public class ShiroController {
    @GetMapping("/unauthorized")
    public ReturnJson unauthorized() {
        return new ReturnJson().invalid();
    }

    @PostMapping("/login")
    public ReturnJson login(String name, String pass, String type) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, pass);
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
}
