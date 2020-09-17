package com.zhao.jwt.security.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhao.jwt.entity.ResponseDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ah
 */
@Component
public class TokenExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse rep, AuthenticationException e) throws IOException, ServletException {
        // 直接返回 json错误
        ResponseDto<Object> result = new ResponseDto<>();
        //20，标识没有token
        result.setCode(403);
        result.setMsg("请求无效，没有有效token");
        ObjectMapper objectMapper = new ObjectMapper();
        rep.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
