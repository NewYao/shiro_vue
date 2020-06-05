package cn.jnx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jnx.model.ReturnJson;
import cn.jnx.model.User;
import cn.jnx.service.UserService;
import cn.jnx.util.rsa.KeyManager;
import cn.jnx.util.rsa.RSAUtil;
import cn.jnx.util.tools.GlobalTools;
import cn.jnx.util.verification_code.Validate;
import cn.jnx.util.verification_code.ValidateCodeUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("/pub")
public class ShiroController {

    private CacheManager cacheManager = CacheManager.getCacheManager("ehcache");
    private Cache cache = cacheManager.getCache("verificationCodeCache");

    @Autowired
    private UserService userService;

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
    public ReturnJson login(String name, String pass, String type, @RequestParam(defaultValue = "") String verifyCode)
            throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String sessionId = subject.getSession().getId().toString();
        Element ele = cache.get(sessionId);
        if (ele == null) {
            return new ReturnJson().fail().message("验证码已过期！");
        }
        String v_code = ele.getValue().toString();
        if (!verifyCode.equalsIgnoreCase(v_code)) {
            return new ReturnJson().fail().message("验证码不正确！");
        }

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

    @PostMapping("/valiCode")
    @ResponseBody
    public ReturnJson valiCode() {
        Validate v = ValidateCodeUtil.getRandomCode();
        Subject subject = SecurityUtils.getSubject();
        String sessionId = subject.getSession().getId().toString();
        Element ele = new Element(sessionId, v.getValue());
        cache.put(ele);// 存放ehcache缓存中
        System.out.println(v.getValue());
        return new ReturnJson().ok().data(v.getBase64Str());
    }

    @PostMapping("/register")
    @ResponseBody
    public ReturnJson register(User user) throws Exception {
        // 根据公钥，解密rsa的秘钥
        String inputPwd = RSAUtil.getTrueStr(user.getPassword());// 解密用户输入的密码成明文
        // 处理密码，生成盐值
        Validate v = ValidateCodeUtil.getRandomCode();
        String salt = v.getValue();
        String encryptionPwd = GlobalTools.md5SimpleHash(inputPwd, ByteSource.Util.bytes(salt).toString());
        user.setSalt(salt);
        user.setPassword(encryptionPwd);
        if(userService.addUser(user,1)) {
        	return new ReturnJson().ok();
        }
        return new ReturnJson().fail().message("注册失败！");
    }

}
