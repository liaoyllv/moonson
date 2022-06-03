package com.jf.moonson.business.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.jf.moonson.business.user.repo.mapper", sqlSessionTemplateRef =
        "moonsonSqlSessionTemplate")
@SpringBootApplication(scanBasePackages = {"com.jf.moonson.business.user"})
@EnableFeignClients(basePackages = {"com.jf.moonson.business.user"})
public class MoonsonBusinessUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonsonBusinessUserApplication.class);
    }
}
