package com.BookStore.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


public class MySimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(MySimpleUrlAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        log.debug("onAuthenticationFailure - method entered");
        log.debug("request parameters");
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            log.debug("{} = {}", paramName, request.getParameter(paramName));
        }
        
        if (request.getParameter("j_password") == null){
            log.warn("Parameter j_password is missing!!!!!");
        }
        
        if (request.getParameter("j_username") == null){
            log.warn("Parameter j_username is missing!!!!!");
        }

        super.onAuthenticationFailure(request, response, exception);
    }
}
