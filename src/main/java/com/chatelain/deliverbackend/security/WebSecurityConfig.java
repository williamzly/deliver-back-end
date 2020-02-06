package com.chatelain.deliverbackend.security;

import com.chatelain.deliverbackend.security.auth.JWTAuthenticationFilter;
import com.chatelain.deliverbackend.security.auth.OpenidAuthenticationProvider;
import com.chatelain.deliverbackend.security.login.JWTLoginFilter;
import com.chatelain.deliverbackend.security.login.WXCodeAuthenticationProvider;
import com.chatelain.deliverbackend.service.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.chatelain.deliverbackend.security.Constant.*;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final WXCodeAuthenticationProvider wxCodeAuthenticationProvider;

    private final OpenidAuthenticationProvider openidAuthenticationProvider;

    public WebSecurityConfig(WXCodeAuthenticationProvider wxCodeAuthenticationProvider, OpenidAuthenticationProvider openidAuthenticationProvider) {
        this.wxCodeAuthenticationProvider = wxCodeAuthenticationProvider;
        this.openidAuthenticationProvider = openidAuthenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(PERMITTED_PAGE_PATH);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(PERMITTED_API_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(wxCodeAuthenticationProvider)
            .authenticationProvider(openidAuthenticationProvider);
    }

}