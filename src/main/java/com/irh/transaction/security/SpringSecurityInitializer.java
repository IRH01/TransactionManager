package com.irh.transaction.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.ServletContext;

/**
 * The Spring Security initializer to register the security filter chain.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer{

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext){
        System.err.println("beforeSpringSecurityFilterChain");
    }

    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext){
        System.err.println("afterSpringSecurityFilterChain");
    }
}
