package com.wany.myuestcbbs.interceptor;

import com.wany.myuestcbbs.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        try {
            JwtUtil.verify(token);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            response.setContentType("application/json:charset=UTF=8");
            String json = "{\"status\":401,\"message\":\"Unauthorized\"}";
            response.getWriter().println(json);
            return false;
        }
    }
}
