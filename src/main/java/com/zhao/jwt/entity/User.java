package com.zhao.jwt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author ah
 * @since 2020-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "user_id")
    protected Integer userId;

    protected String username;

    protected String password;

    /**
     * 可用
     */
    protected Boolean enable;

    /**
     * 锁
     */
    protected Boolean locked;

    protected Boolean expired;

}
