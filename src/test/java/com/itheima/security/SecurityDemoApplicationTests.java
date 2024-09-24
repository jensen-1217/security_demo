package com.itheima.security;

import com.itheima.security.mapper.TbUserMapper;
import com.itheima.security.pojo.TbUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.channels.Pipe;

@SpringBootTest
class SecurityDemoApplicationTests {

//    @Autowired
//    private TbUserMapper mapper;
//
//    @Test
//    void contextLoads() {
//        TbUser user = mapper.findByUserName("zhangsan");
//        System.out.println(user);
//    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    $2a$10$s0V2JCKUIt0nyIBjPYBj4eENkhdbGF4jM3qa0sbGtcx1/ExZMXbRm
//    $2a$10$GVSNrqlPLYG0HsKi2HAO4eU4D0xHxkmls//5cmLYawCBWH9BGwu66
//    $2a$10$MAo50iN180XkiSZwygF4Pu6iMCb8hN4s5/69BebPLZqgiwe.Cl2Ay
//    $2a$10$XkUnGk3Fb0nOQ8SH9c61Set5IXoD7F8nKgvOj0.mqzY8JxkWgfjKK
//    $2a$10$6/p9b2F4QD8RK3LRdZug3ehACKwZNToIGHOTuLLDOgVKrboo4Rcg6
    @Test
    public void test01(){
        for (int i = 0; i < 5; i++) {
            System.out.println(bCryptPasswordEncoder.encode("123456"));
        }
    }

    @Test
    public void testMatch(){
        boolean matches =  bCryptPasswordEncoder.matches("123456", "$2a$10$s0V2JCKUIt0nyIBjPYBj4eENkhdbGF4jM3qa0sbGtcx1/ExZMXbRm");
        System.out.println(matches);//返回值为true, 则代表验证通过; 反之, 验证不通过
    }

}
