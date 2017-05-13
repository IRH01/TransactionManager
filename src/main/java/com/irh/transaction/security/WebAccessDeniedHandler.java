package com.irh.transaction.security;

import com.irh.transaction.exceptions.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The handler to handle an access denied failure.
 *
 * <p> For view request, redirects the user to the URL specified in the {@link WebAccessDeniedHandler#errorUrl}; for API
 * request, sends back a JSON representing an access denied response. </p>
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class WebAccessDeniedHandler implements AccessDeniedHandler{

    /**
     * The URL of the error page.
     */
    private final String errorUrl;

    /**
     * Creates a new instance of the {@link WebAccessDeniedHandler} class with the given error URL.
     *
     * @param errorUrl the URL of the error page.
     * @throws IllegalArgumentException if the argument is null or empty.
     */
    public WebAccessDeniedHandler(String errorUrl){
        if(errorUrl == null || errorUrl.trim().length() == 0){
            throw new IllegalArgumentException("The errorUrl cannot be null.");
        }
        this.errorUrl = errorUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException{
        if(request.getRequestURI().startsWith("/api")){
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(String.format("{\"code\":\"%s\", \"message\":\"%s\"}",
                    ApiResponse.ACCESS_DENIED_CODE, ApiResponse.ACCESS_DENIED_MSG));
            response.getWriter().flush();
        }else{
            response.sendRedirect(errorUrl);
        }
    }
}
