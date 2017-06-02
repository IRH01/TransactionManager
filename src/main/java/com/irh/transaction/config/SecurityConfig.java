package com.irh.transaction.config;

import com.irh.transaction.security.WebAccessDeniedHandler;
import com.irh.transaction.security.WebAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The config for spring security.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/static/**", "error.html");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic().authenticationEntryPoint(new WebAuthenticationEntryPoint("/login"));
        http.exceptionHandling().accessDeniedHandler(new WebAccessDeniedHandler("/error.html"));
        http.authorizeRequests()
                .antMatchers("/login", "/api/account/login", "/api/account/logout").permitAll()
                .antMatchers("/account/account/**", "/api/account/account/**").hasAuthority("account")
                .antMatchers("/account/role/**", "/api/account/role/**").hasAuthority("permission")
                .antMatchers("/role/**", "/api/role/**").hasAuthority("permission")
                .antMatchers("/api/product/category").hasAnyAuthority("product", "branch", "marketing")
                .antMatchers("/product/**", "/api/product/**").hasAuthority("product")
                .antMatchers("/branch/**", "/api/branch/**").hasAuthority("branch")
                .antMatchers("/marketing/**", "/api/marketing/**").hasAuthority("marketing")
                .antMatchers("/statistic/**", "/api/statistic/**").hasAuthority("data")
                .antMatchers("/finance/**", "/api/finance/**").hasAuthority("finance")
                .anyRequest().authenticated();
    }
}
