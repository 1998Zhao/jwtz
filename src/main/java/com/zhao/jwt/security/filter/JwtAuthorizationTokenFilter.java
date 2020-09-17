package com.zhao.jwt.security.filter;

import com.zhao.jwt.security.MyUserDetailsServiceImpl;

import com.zhao.jwt.security.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ah
 */
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
    private final MyUserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final String tokenHeader;
    public JwtAuthorizationTokenFilter(MyUserDetailsServiceImpl userDetailsService,
                                       JwtTokenUtil jwtTokenUtil, @Value("${jwt.token}") String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenHeader = tokenHeader;
    }
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request r
     * @param response r
     * @param filterChain f
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(this.tokenHeader);
        String username = null;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request, response);
    }
}
