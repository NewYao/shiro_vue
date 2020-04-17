package cn.jnx.common.Exception;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jnx.model.ReturnJson;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnJson handleException(Exception e) {
        System.out.println("系统错误："+e.getMessage());
        return new ReturnJson().error().message("系统错误，请稍后重试！");
    }

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

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ReturnJson handle401() {
        return new ReturnJson().invalid().message("您没有权限访问！");
    }

}
