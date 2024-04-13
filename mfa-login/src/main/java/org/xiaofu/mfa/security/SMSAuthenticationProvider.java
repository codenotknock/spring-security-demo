package org.xiaofu.mfa.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author xiaofu
 * @date 2024/4/13 16:18
 * @des
 */
public class SMSAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String sms = String.valueOf(authentication.getCredentials());

        // 检查校验逻辑 检查手机验证码是否正确 （redis）
        //
        // checkCode(username, sms)

        if (true) {
            return new SMSAuthentication(username, sms);
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SMSAuthenticationProvider.class.isAssignableFrom(aClass);
    }
}
