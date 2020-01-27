package com.yang.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUse {
    // 设置过期时间
    private static final long overDeuTime = 1440 * 60 * 1000;

    // 设置秘钥
    private static final String tokenSecret = "fde35b32-0f47-46be-ae2a-49bcb7ed7d7f";

    // 生成token
    public static String sign(String username, Integer userId) {
        // 设置过期时间
        Date data = new Date(System.currentTimeMillis() + overDeuTime);
        // 使用token私钥加密
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
        // 设置头部信息
        Map<String, Object> requestHeader = new HashMap<>(2);
        requestHeader.put("Type", "Jwt");
        requestHeader.put("alg", "HS256");
//        long dt = new Date().getTime();
        // 返回带有用户信息的签名
        try{
            return JWT.create().withHeader(requestHeader)
                    .withClaim("username", username)
                    .withClaim("userId", userId)
                    .withExpiresAt(data)
                    .sign(algorithm);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // 验证token是否正确，返回boolean值
    public static boolean tokenVerify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 验证
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 获取登陆用户token中的用户ID
    public static int getUserID(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("userId").asInt();
        }catch (Exception e){
            return 0;
        }
    }
}
