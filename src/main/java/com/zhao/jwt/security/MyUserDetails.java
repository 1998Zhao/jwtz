package com.zhao.jwt.security;

import com.zhao.jwt.entity.Role;
import com.zhao.jwt.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ah
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyUserDetails extends User implements UserDetails {
    List<Role> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role r:roles){
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
