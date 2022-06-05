package com.jf.moonson.business.account;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(basePackages = "com.jf.moonson.business.account.repo.mapper")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.jf.moonson.business.account"}, exclude =
        {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class MoonsonBusinessAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonsonBusinessAccountApplication.class, args);
    }

}
