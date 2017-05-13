package com.irh.transaction;

import com.irh.transaction.config.AppConfig;
import com.irh.transaction.config.SecurityConfig;
import com.irh.transaction.config.WebConfig;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * The Spring MVC web application initializer.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStartup(ServletContext context) throws ServletException{
        super.onStartup(context);
        context.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
    }

    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class[]{AppConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings(){
        return new String[]{"/"};
    }
}
