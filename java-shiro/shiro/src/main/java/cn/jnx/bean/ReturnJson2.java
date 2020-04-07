package cn.jnx.bean;

/**
 * Created with Eclipse
 *
 * @Author JH
 * @Description 接口返回对象
 * @Date 2020年4月7日
 * @Time 13:45:41
 */
public class ReturnJson2 {

    private int code;
    private String msg;
    private Object data;

    private ReturnJson2() {};

    public static ReturnJson2 ok() {
        return new ReturnJson2().code(200);
    }

    public static ReturnJson2 ok(String msg) {
        return new ReturnJson2().code(200).msg(msg);
    }

    public static ReturnJson2 ok(String msg, Object data) {
        return new ReturnJson2().code(200).msg(msg).data(data);
    }

    public static ReturnJson2 error() {
        return new ReturnJson2().code(500);
    }

    public static ReturnJson2 error(String msg) {
        return new ReturnJson2().code(500).msg(msg);
    }

    public static ReturnJson2 error(String msg, Object data) {
        return new ReturnJson2().code(500).msg(msg).data(data);
    }

    private ReturnJson2 code(int code) {this.code = code;return this;};

    private ReturnJson2 msg(String msg) {this.msg = msg;return this;};

    private ReturnJson2 data(Object data) {this.data = data;return this;}

    public int getCode() {return code;}

    public String getMsg() {return msg;}

    public Object getData() {return data;};
    

}
