package cn.jnx.util.verification_code;

import java.io.Serializable;

public class Validate implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String Base64Str;       //Base64 值
    private String value;           //验证码值
    
    public String getBase64Str() {
        return Base64Str;
    }
    public void setBase64Str(String base64Str) {
        Base64Str = base64Str;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
