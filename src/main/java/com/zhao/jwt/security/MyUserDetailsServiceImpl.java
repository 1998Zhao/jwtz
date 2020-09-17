package com.zhao.jwt.security;

import com.zhao.jwt.entity.User;
import com.zhao.jwt.mapper.UserMapper;
import com.zhao.jwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ah
 */
@Service
public class MyUserDetailsServiceImpl extends UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = super.getUserByUsername(s);
        if (user==null){
            throw new UsernameNotFoundException("账户不存在");
        }
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUserId(user.getUserId());
        userDetails.setUsername(user.getUsername());
        userDetails.setEnable(user.getEnable());
        userDetails.setExpired(user.getExpired());
        userDetails.setPassword(user.getPassword());
        userDetails.setLocked(user.getLocked());
        userDetails.setRoles(userMapper.getUserRolesById(user.getUserId()));
        return userDetails;
    }
}
