package com.jf.moonson.business.user;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@MapperScan(basePackages = "com.jf.moonson.business.user.repo.mapper", sqlSessionTemplateRef =
        "moonsonSqlSessionTemplate")
@SpringBootApplication(scanBasePackages = {"com.jf.moonson.business.user"}, exclude =
        {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableFeignClients(basePackages = {"com.jf.moonson.business.user"})
public class MoonsonBusinessUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonsonBusinessUserApplication.class);
    }
}
