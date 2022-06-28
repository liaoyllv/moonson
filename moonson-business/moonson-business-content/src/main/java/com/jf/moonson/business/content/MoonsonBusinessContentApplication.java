package com.jf.moonson.business.content;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@MapperScan(basePackages = "com.jf.moonson.business.content.repo.mapper", sqlSessionTemplateRef =
        "moonsonSqlSessionTemplate")
@SpringBootApplication(scanBasePackages = {"com.jf.moonson.business.content"}, exclude =
        {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableFeignClients(basePackages = {"com.jf.moonson.business.content"})
public class MoonsonBusinessContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonsonBusinessContentApplication.class);
    }
}
