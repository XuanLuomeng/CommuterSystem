package cn.gduf.commuterSystem.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;

/**
 * 密码加密工具类
 *
 * @author LuoXuanwei
 * @date 2023/10/14 17:35
 */
public class EncryptByMd5 {
    private String password;
    private String salt;
    private SimpleHash simpleHash;

    public EncryptByMd5() {
    }

    public EncryptByMd5(String password) {
        this.password = password;
        this.salt = UUID.randomUUID().toString();
        this.simpleHash = new SimpleHash("MD5", password, salt, 5);
    }

    public EncryptByMd5(String password, String salt) {
        this.password = password;
        this.salt = salt;
        this.simpleHash = new SimpleHash("MD5", password, salt, 5);
    }

    public String getSalt() {
        return salt;
    }

    public String getSimpleHash() {
        return simpleHash.toString();
    }
}
