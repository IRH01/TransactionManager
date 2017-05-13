package com.irh.transaction.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * This authentication entry point sets the HTTP response for request.
 *
 * <p> Redirects to user to the URL specified in {@link WebAuthenticationEntryPoint#loginUrl} if not authenticated.
 * </p>
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint{

    /**
     * The URL of the login page.
     */
    private final String loginUrl;

    /**
     * Creates a new instance of the {@link WebAuthenticationEntryPoint} class with the given login URL.
     *
     * @param loginUrl the URL of the login page.
     * @throws IllegalArgumentException if the argument is null or empty.
     */
    public WebAuthenticationEntryPoint(String loginUrl){
        if(loginUrl == null || loginUrl.trim().length() == 0){
            throw new IllegalArgumentException("The errorUrl cannot be null.");
        }
        this.loginUrl = loginUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException{
        response.sendRedirect(loginUrl + (request.getRequestURI().length() > 0 ? "?redirect="
                + URLEncoder.encode(request.getRequestURI(), "UTF-8") : ""));
    }
}