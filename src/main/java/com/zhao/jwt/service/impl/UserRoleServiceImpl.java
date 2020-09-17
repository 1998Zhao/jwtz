package com.zhao.jwt.service.impl;

import com.zhao.jwt.entity.UserRole;
import com.zhao.jwt.mapper.UserRoleMapper;
import com.zhao.jwt.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
