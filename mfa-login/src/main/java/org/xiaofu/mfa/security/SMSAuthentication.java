package org.xiaofu.mfa.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author xiaofu
 * @date 2024/4/13 16:24
 * @des
 */
public class SMSAuthentication extends UsernamePasswordAuthenticationToken {
    public SMSAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public SMSAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
