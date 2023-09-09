package com.express.freight.util;

import com.express.freight.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {

    JWTUtil jwtUtil = new JWTUtil();

    @Resource(name = "userService")
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println("PREHANDLE : " + jwt);

        if (StringUtils.isEmpty(jwt)) {
            System.out.println("The value of jwt in the request header is null... ");

            throw new IllegalAccessException(" jwt value cannot be null!!!");
        }

        String userId = JWTUtil.getUserId(jwt);

        if (!userService.isUser(userId)) {
            System.out.println(" User Not found!!! USER_ID : " + userId);
            return false;
        }

        return true;
    }

}
