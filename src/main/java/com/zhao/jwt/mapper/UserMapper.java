package com.zhao.jwt.mapper;

import com.zhao.jwt.entity.Role;
import com.zhao.jwt.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ah
 * @since 2020-09-13
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取完整的user
     * @param id id
     * @return 用户
     */
    List<Role> getUserRolesById(Integer id);
}
