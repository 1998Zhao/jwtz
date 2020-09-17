package com.zhao.jwt.service.impl;

import com.zhao.jwt.entity.Role;
import com.zhao.jwt.mapper.RoleMapper;
import com.zhao.jwt.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
