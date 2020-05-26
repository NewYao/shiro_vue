package cn.jnx.util.tools;

import org.apache.shiro.crypto.hash.SimpleHash;

public class GlobalTools {
    /**
     * shiro密码统一处理工具
     * @param password
     * @param salt
     * @return
     */
    public static String md5SimpleHash(String password, String salt) {
        return new SimpleHash("MD5", password, salt, 1024).toString();// 加密后的密码
    }
}
