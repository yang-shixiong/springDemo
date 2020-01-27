package com.yang.util;

import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;


/**
 * 生成Token工具类
 */
public class TokenProcessor {
    private TokenProcessor(){};
    private static final TokenProcessor instance = new TokenProcessor();
    public static TokenProcessor getInstance(){
        return instance;
    }

    public String makeToken(){
        String token = UUID.randomUUID().toString().replaceAll("-","") + "";
        try{
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(md5);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

}
