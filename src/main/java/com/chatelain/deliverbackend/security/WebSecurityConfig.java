package com.chatelain.deliverbackend.security;

import com.chatelain.deliverbackend.security.auth.JWTAuthenticationFilter;
import com.chatelain.deliverbackend.security.auth.IdAuthenticationProvider;
import com.chatelain.deliverbackend.security.login.JWTLoginFilter;
import com.chatelain.deliverbackend.security.login.WXCodeAuthenticationProvider;
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

    private final IdAuthenticationProvider idAuthenticationProvider;

    public WebSecurityConfig(WXCodeAuthenticationProvider wxCodeAuthenticationProvider, IdAuthenticationProvider idAuthenticationProvider) {
        this.wxCodeAuthenticationProvider = wxCodeAuthenticationProvider;
        this.idAuthenticationProvider = idAuthenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(PERMITTED_PAGE_PATH);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(PERMITTED_API_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .exceptionHandling().authenticationEntryPoint(new SimpleAuthenticationEntryPoint());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(wxCodeAuthenticationProvider)
            .authenticationProvider(idAuthenticationProvider);
    }

}