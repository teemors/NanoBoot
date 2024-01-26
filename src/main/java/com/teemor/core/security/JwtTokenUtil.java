/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.teemor.core.security;

import cn.hutool.core.codec.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * <p>jwt token工具类</p>
 * <pre>
 *     jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 * </pre>
 *
 * @author lujing
 * @Date 2018/12/25 10:59
 */
public class JwtTokenUtil {


    public static final String USER_ID = "uid";

    public static final String USER_TYPE = "utp";

    /**
     * 默认jwt的过期时间 ms
     */
    private final Long defaultExpiredDate;

    /**
     * jwt的秘钥
     */
    private final SecretKey secretKey;

    public JwtTokenUtil(String jwtSecret, Long defaultExpiredDate) {
        this.defaultExpiredDate = defaultExpiredDate;
        this.secretKey = Keys.hmacShaKeyFor(Base64.decode(jwtSecret.getBytes()));
    }

    public JwtTokenUtil(String jwtSecret) {
        this.defaultExpiredDate = 24 * 60 * 60 * 1000L;
        this.secretKey = Keys.hmacShaKeyFor(Base64.decode(jwtSecret));
    }

    /**
     * 获取用户名从token中
     */
    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接收者
     */
    public Set<String> getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取jwt的payload部分
     */
    public Claims getClaimFromToken(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }


    /**
     * 生成token,根据userId和默认过期时间
     */
    public String generateToken(Map<String, Object> claims) {
        final Date expirationDate = new Date(System.currentTimeMillis() + defaultExpiredDate * 1000);
        Claims realClaim = Jwts.claims()
                .add(claims)
                .build();

        return generateToken(expirationDate, realClaim);
    }

    /**
     * 生成token,根据userId和过期时间
     */
    public String generateToken(Date expirationDate, Map<String, Object> claims) {


        return Jwts.builder()
                .signWith(secretKey)
                .claims(claims)
                .expiration(expirationDate)
                .compact();

    }


}