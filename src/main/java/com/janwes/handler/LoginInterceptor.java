package com.janwes.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.handler
 * @date 2021/9/9 16:00
 * @description 拦截器
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("<<< interceptor start......");
        String queryString = request.getQueryString();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.info("username:{},password:{}", username, password);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("{\"code\":\"401\",\"message\":\"请登录\",\"data\":\"\"}");
        return false;
    }
}
