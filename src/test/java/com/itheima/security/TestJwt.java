package com.itheima.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @author : itheima
 * @date : 2022/11/4 16:46
 * @description :
 */
public class TestJwt {


    /**
     * @desc 测试生成jwt token
     */
    @Test
    public void testGenerateToken() {
        String token = Jwts.builder().setId(UUID.randomUUID().toString())//token票据的唯一标识
                .setSubject("jrzs")
                .claim("name", "zhangsan")//把自定义的信息维护到jwt的载荷中
                .claim("permission", "crud")
                .setIssuedAt(new Date())//设置票据的签发时间
                .setExpiration(new DateTime().plusMinutes(30).toDate())//设置票据的过期时间
                .signWith(SignatureAlgorithm.HS256, "itheima")//设置签证生成的加密算法和秘钥
                .compact();
        System.out.println(token);
    }

    /**
     * @desc 验证票据是否合法
     * 只要票据解析出现异常，说明票据已经失效了
     *  票据过期异常：io.jsonwebtoken.ExpiredJwtException: JWT expired at 2022-11-04T16:57:42Z. Current time: 2022-11-04T16:58:13Z
     *  票据被篡改：io.jsonwebtoken.SignatureException:
     */
    @Test
    public void testCheckToken() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmZWIwODdmYi04NGI0LTRkYjMtYjQ2Mi1iYjk3Njg3MDRlZDAiLCJzdWIiOiJqcnpzIiwibmFtZSI6InpoYW5nc2FuIiwicGVybWlzc2lvbiI6ImNydWQiLCJpYXQiOjE3MjY0ODgzMjIsImV4cCI6MTcyNjQ5MDEyMn0.in_wHl-_N6duiJUhEuACED8VUmgBsy3gmV0roZsryd0";
        Claims cl = Jwts.parser().setSigningKey("itheima").parseClaimsJws(token).getBody();
        Object name = cl.get("name");
        System.out.println(name);
    }




}
