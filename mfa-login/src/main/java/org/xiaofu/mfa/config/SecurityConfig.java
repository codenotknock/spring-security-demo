package org.xiaofu.mfa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.xiaofu.mfa.security.CustomAuthenticationFilter;
import org.xiaofu.mfa.security.SMSAuthenticationProvider;
import org.xiaofu.mfa.security.UsernamePasswordAuthenticationProvider;

/**
 * @author xiaofu
 * @date 2024/4/13 16:30
 * @des
 */
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFilter initialAuthenticationFilter;

    @Autowired
    private SMSAuthenticationProvider SMSAuthenticationProvider;

    @Autowired
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(SMSAuthenticationProvider)
                .authenticationProvider(usernamePasswordAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.addFilterAt(
                initialAuthenticationFilter,
                BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
