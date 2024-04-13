package org.xiaofu.mfa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author xiaofu
 * @date 2024/4/13 16:20
 * @des
 */

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String username = httpServletRequest.getHeader("username");
        String password = httpServletRequest.getHeader("password");
        String sms = httpServletRequest.getHeader("sms");

        if (sms == null) {
            // 第一因素：账号密码验证
            Authentication a = new UsernamePasswordAuthentication(username, password);
            manager.authenticate(a);
        } else {
            // 第二因素：手机号验证码验证或者其他
            Authentication a = new SMSAuthentication(username, sms);
            manager.authenticate(a);

            // 第二因素认证通过，说明认证成功，向用户发放令牌或者是其它
            // jwt 生成token逻辑，此处仅仅是deo，返回给用户
            String token = UUID.randomUUID().toString();
            httpServletResponse.setHeader("token", token);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}
