package com.itheima.security.service;

import com.itheima.security.mapper.TbUserMapper;
import com.itheima.security.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jensen
 * @date 2024-09-16 22:37
 * @description
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser user = tbUserMapper.findByUserName(username);
        if (user==null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //构建认证明细对象
        //获取用户权限
        List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
//        User user1 = new User(user.getUsername(),user.getPassword(),list);
        UserDetails user1 = User.builder().username(user.getUsername())
                .password(user.getPassword())
                .authorities(list).build();
        return user1;

    }
}
