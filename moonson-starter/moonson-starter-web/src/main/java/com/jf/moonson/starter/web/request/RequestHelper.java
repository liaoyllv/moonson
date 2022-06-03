package com.jf.moonson.starter.web.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求配置
 */
@Configuration
public class RequestHelper {

    @Value("${moonson.host.app}")
    private String moonsonAppHost;
    @Value("${moonson.host.api}")
    private String moonsonApiHost;

    public static String getMapSource() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return (String) request.getAttribute("mapSource");
    }

}