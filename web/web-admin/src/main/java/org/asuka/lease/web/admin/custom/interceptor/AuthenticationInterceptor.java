package org.asuka.lease.web.admin.custom.interceptor;

import io.jsonwebtoken.Claims;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.asuka.lease.common.exception.SystemUserException;
import org.asuka.lease.common.login.LoginUser;
import org.asuka.lease.common.login.LoginUserHolder;
import org.asuka.lease.common.result.ResultCodeEnum;
import org.asuka.lease.common.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("access-token");
        if (token == null) {
            throw new SystemUserException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        Claims claims = JwtUtil.parseToken(token);
        //存储到thread local
        Long userId = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);
        LoginUserHolder.setLoginUser(new LoginUser(userId, username));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clean();
    }
}
