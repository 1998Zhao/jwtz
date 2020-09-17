package com.zhao.jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhao.jwt.entity.User;
import com.zhao.jwt.mapper.UserMapper;
import com.zhao.jwt.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ah
 * @since 2020-09-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByUsername(String s){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",s);
        return userMapper.selectOne(wrapper);
    }
}
