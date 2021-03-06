package cn.jnx.common.Exception;

import javax.crypto.BadPaddingException;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jnx.model.ReturnJson;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有业务异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ReturnJson handleBusinessException(CustomException e) {
        return new ReturnJson().fail().message(e.getMessage());
    }

    /**
     * RSA解密异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(BadPaddingException.class)
    @ResponseBody
    public ReturnJson handleBadPaddingException(Exception e) {
        return new ReturnJson().fail().message(e.getMessage());
    }

    // 捕捉shiro的异常
//    @ExceptionHandler(UnauthenticatedException.class)
//    @ResponseBody
//    public ReturnJson handle401() {
//        System.out.println("登录全局捕获器");
//        return new ReturnJson().unLogin().message("您没有登录！");
//    }

    // 捕捉shiro的异常
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ReturnJson handle403() {
        System.out.println("权限全局捕获器");
        return new ReturnJson().invalid().message("您无此权限！");
    }

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ReturnJson shiro() {
        System.out.println("shiro全局捕获器");
        return new ReturnJson().invalid().message("您没有权限访问！");
    }

    /**
     * 处理所有不可知的异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnJson handleException(Exception e) {
        System.out.println("系统错误：" + e.getStackTrace());
        e.printStackTrace();
        return new ReturnJson().error().message("系统错误，请稍后重试！");
    }
}
