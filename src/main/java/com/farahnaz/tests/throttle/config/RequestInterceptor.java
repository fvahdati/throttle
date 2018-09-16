package com.farahnaz.tests.throttle.config;

import com.farahnaz.tests.throttle.Utils.Contants;
import com.farahnaz.tests.throttle.biz.ClientThrottle;
import com.farahnaz.tests.throttle.model.RequestStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {


    Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("intercepting".concat(request.getScheme()));


        Integer clientID = Integer.valueOf(request.getParameter("clientID"));

        ClientThrottle clientThrottle =  ClientThrottle.getInstance();

        RequestStatus requestStatus =  clientThrottle.verifyAccessCap(clientID);
        ObjectMapper mapper = new ObjectMapper();

        if (requestStatus.accessCode != Contants.CLIENT_ACCESS_PERMITTED_CODE) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

            response.getWriter().write(mapper.writeValueAsString(requestStatus));

            response.getWriter().flush();
            response.getWriter().close();
            return  false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
