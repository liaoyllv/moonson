package com.jf.moonson.starter.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 */
@Slf4j
@Configuration
public class FeignAutoConfiguration {

    public FeignAutoConfiguration() {
        log.info("Initializing FeignAutoConfiguration");
    }

    @Bean
    public FeignBasicAuthRequestInterceptor interceptor() {
        return new FeignBasicAuthRequestInterceptor(new ObjectMapper());
    }
}
