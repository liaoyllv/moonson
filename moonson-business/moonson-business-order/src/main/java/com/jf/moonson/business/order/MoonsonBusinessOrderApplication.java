package com.jf.moonson.business.order;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.jf.moonson.business.order.repo.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jf.moonson.business.order"})
@SpringBootApplication(scanBasePackages = {"com.jf.moonson.business.order"}, exclude =
        {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class MoonsonBusinessOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonsonBusinessOrderApplication.class, args);
        System.out.println("app started");
    }

}
