package com.zhao.jwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhao.jwt.entity.ResponseDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ah
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        // 返回我们的自定义json
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<Object> result = new ResponseDto<>();
        //50，标识有token，但是该用户没有权限
        result.setCode(50);
        result.setMsg("请求无效，没有有效token");
        // 返回我们的自定义json
        resp.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
