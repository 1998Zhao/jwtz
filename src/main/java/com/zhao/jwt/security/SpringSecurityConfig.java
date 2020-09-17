package com.zhao.jwt.security;

import com.zhao.jwt.security.ExceptionHandler.TokenExceptionHandler;
import com.zhao.jwt.security.filter.JwtAuthorizationTokenFilter;
import com.zhao.jwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ah
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsServiceImpl userDetailsService;
    @Autowired
    TokenExceptionHandler tokenExceptionHandler;
    @Autowired
    JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers(HttpMethod.GET,"/token");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(tokenExceptionHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
                //.exceptionHandling().authenticationEntryPoint()
    }
}
