package com.wany.myuestcbbs.util;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    public static String SECRET = "!@#&RQQSDFG";

    public static String getToken(Map<String, String> payload) {
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();

        payload.forEach(builder::withClaim);

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); // 默认7天过期

        builder.withExpiresAt(instance.getTime()); // 设置过期时间
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verify(String token) {
        return com.auth0.jwt.JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static String getPayload(String token, String key) {
        return verify(token).getClaim(key).asString();
    }
}
