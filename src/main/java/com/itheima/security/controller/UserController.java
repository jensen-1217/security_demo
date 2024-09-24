package com.itheima.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * @author by itheima
 * @Date 2022/1/22
 * @Description
 */
@RestController
public class UserController {


    @PreAuthorize("hasAuthority('P5')")
    @GetMapping("/hello")
    public String hello(){
        return "hello security";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/say")
    public String say(){
        return "say security";
    }

    @PermitAll
    @GetMapping("/register")
    public String register(){
        return "register security";
    }
}
