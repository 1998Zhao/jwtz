package com.zhao.jwt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhao.jwt.entity.User;
import com.zhao.jwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ah
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/jwt/user")
public class UserController {
    @Qualifier("userServiceImpl")
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/{id}")
    public User get(@PathVariable Integer id){
        User user = userService.getById(id);
        System.out.println(user);
        return userService.getOne(new QueryWrapper<User>().eq("user_id",id));
    }
}
