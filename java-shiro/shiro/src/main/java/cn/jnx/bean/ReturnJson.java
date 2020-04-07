package cn.jnx.bean;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created with Eclipse
 *
 * @Author JH
 * @Description 接口返回对象
 * @Date 2018-02-26
 * @Time 16:03
 */
@Component
public class ReturnJson extends HashMap<String, Object> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ReturnJson() {}

    /**
     * 成功
     * 
     * @return
     */
    public ReturnJson ok() {
        this.put("result", "success");
        this.put("code", 200);
        return this;
    }

    /**
     * 失败
     * 
     * @return
     */
    public ReturnJson fail() {
        this.put("result", "fail");
        this.put("code", 400);
        return this;
    }

    /**
     * 无权限
     * 
     * @param code
     * @return
     */
    public ReturnJson invalid() {
        this.put("result", "invalid");
        this.put("code", 403);
        return this;
    }

    /**
     * 服务器错误
     * 
     * @param code
     * @return
     */
    public ReturnJson error() {
        this.put("result", "error");
        this.put("code", 500);
        return this;
    }

    /**
     * 描述
     * 
     * @param message
     * @return
     */
    public ReturnJson message(String s) {this.put("msg", s);return this;}

    /**
     * 简单数据数据
     * 
     * @param message
     * @return
     */
    public ReturnJson data(String s, Object v) {this.put(s, v);return this;}

    /**
     * 数据
     * 
     * @param message
     * @return
     */
    public ReturnJson data(Object o) {this.put("data", o);return this;}
}
