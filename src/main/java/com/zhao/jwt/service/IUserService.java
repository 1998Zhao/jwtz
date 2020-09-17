package com.zhao.jwt.service;

import com.zhao.jwt.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ah
 * @since 2020-09-13
 */
public interface IUserService extends IService<User> {

    User getUserByUsername(String s);
}
