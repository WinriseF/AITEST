package com.java.aitest.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.java.aitest.Entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "YWphejdEMnMzT05iNklYc0dqeGs4WmY5N0dEaTdMR1l5bGlBeU9VbkJIZz0";

    // Token 有效期（例如：24小时）
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    // 定义加密算法
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    /**
     * 生成JWT
     * @param user 用户实体
     * @return 生成的Token字符串
     */
    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername()) // 将用户名作为subject
                .withClaim("userId", user.getId()) // 添加自定义claim: userId
                .withClaim("email", user.getEmail()) // 添加自定义claim: email
                .withIssuedAt(new Date()) // 设置签发时间
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .sign(algorithm); // 使用算法进行签名
    }

    /**
     * 验证Token并解码
     * @param token Token字符串
     * @return 解码后的JWT对象，如果验证失败则返回null
     */
    private DecodedJWT verifyAndDecodeToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception){
            // 验证失败（签名错误、Token过期等）
            return null;
        }
    }

    /**
     * 从Token中提取用户名
     * @param token Token字符串
     * @return 用户名
     */
    public String extractUsername(String token) {
        DecodedJWT decodedJWT = verifyAndDecodeToken(token);
        return (decodedJWT != null) ? decodedJWT.getSubject() : null;
    }


    /**
     * 验证Token是否有效
     * @param token Token字符串
     * @param user  用于比对的用户实体
     * @return 如果token有效且属于该用户，则返回true
     */
    public Boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username != null && username.equals(user.getUsername()));
    }
}