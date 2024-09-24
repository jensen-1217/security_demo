package com.itheima.security.config;

import com.itheima.security.filter.AuthenticationFilter;
import com.itheima.security.filter.MyUserNamePasswordAuthenticationFilter;
import com.itheima.security.handler.MyAccessDeniedHandler;
import com.itheima.security.handler.MyAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity//开启web安全设置生效
//开启SpringSecurity相关注解支持
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 构建认证服务，并将对象注入spring IOC容器，用户登录时，会调用该服务进行用户合法信息认证
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        //从内存获取用户认证信息的服务类（了解）后期用户的信息要从表中获取
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        //构建用户,真实开发中用户信息要从数据库加载构建
//        UserDetails u1 = User
//                .withUsername("itcast")
//                .password("{noop}123456")//{noop}:no operration--》表示登录时对避免不做任何操作，说白了就是明文比对
//                .authorities("P5", "ROLE_ADMIN")//用户的权限信息
//                .build();
//        UserDetails u2 = User
//                .withUsername("itheima")
//                .password("{noop}123456")
//                .authorities("P7", "ROLE_SELLER","ROLE_ADMIN")//如果角色也作为一种权限资源，则角色名称的前缀必须加ROLE_
//                .build();
//        inMemoryUserDetailsManager.createUser(u1);
//        inMemoryUserDetailsManager.createUser(u2);
//        return inMemoryUserDetailsManager;
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//开启默认form表单登录方式
                .and()
                .logout()//登出用默认的路径登出 /logout
                .permitAll()//允许所有的用户访问登录或者登出的路径
                .and()
                .csrf().disable()//启用CSRF,防止CSRF攻击
                .authorizeRequests()
        .anyRequest().authenticated();//授权方法，该方法后有若干子方法进行不同的授权规则处理
                //允许所有账户都可访问（不登录即可访问）,同时可指定多个路径
//                .antMatchers("/register").permitAll()
                //开发方式1：基于配置
//                .antMatchers("/a1","/a2").hasRole("seller")//拥有seller角色的用户可访问a1和a2资源
                //拥有指定的任意角色都可以访问对应资源
//                .antMatchers("/b1").hasAnyRole("manager1","manager2")
                //用户任意指定的aa bb都可以访问c1资源
//                .antMatchers("/c1").hasAnyAuthority("aa","bb")
//                .antMatchers("/d").denyAll()//拒绝任意用户访问
//                .antMatchers("/e").anonymous()//允许匿名访问
                //指定IP可以访问
//                .antMatchers("/f").hasIpAddress("localhost/82")
//                .antMatchers("/hello").hasAuthority("P5") //具有P5权限才可以访问
//                .antMatchers("/say").hasRole("ADMIN") //具有ROLE_ADMIN 角色才可以访问
//                .anyRequest().authenticated(); //除了上边配置的请求资源，其它资源都必须授权才能访问

                //坑-过滤器要添加在默认过滤器之前，否则，登录失效
        http.addFilterBefore(myUserNamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //配置授权过滤器，过滤一切资源
        http.addFilterBefore(authenticationFilter(),MyUserNamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler())
        .authenticationEntryPoint(new MyAuthenticationEntryPoint());
    }


    @Bean
    public MyUserNamePasswordAuthenticationFilter myUserNamePasswordAuthenticationFilter() throws Exception {
        //设置默认登录路径
        MyUserNamePasswordAuthenticationFilter myUserNamePasswordAuthenticationFilter =
                new MyUserNamePasswordAuthenticationFilter("/myLogin");
        myUserNamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return myUserNamePasswordAuthenticationFilter;
    }

    /**
     * 自定义授权过滤器
     * 过滤一切被访问的资源
     * @return
     */
    @Bean
    public AuthenticationFilter authenticationFilter(){
       return new AuthenticationFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}