package com.jf.moonson.starter.db;

import com.alibaba.druid.proxy.jdbc.DataSourceProxy;
import com.alibaba.druid.proxy.jdbc.DataSourceProxyImpl;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * @author LiuGong
 * @version $
 * @since 2019年12月04日 19:37
 */
@Slf4j
// 防止无法解析
// @AutoConfigureBefore({DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@Configuration
public class MoonsonDataSourceAutoConfiguration {

    public MoonsonDataSourceAutoConfiguration() {
        log.info("Initializing MoonsonDataSourceAutoConfiguration");
    }

    @Bean(name = "moonsonDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.moonson")
    public DataSource moonsonDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory moonsonSqlSessionFactory(@Qualifier("moonsonDataSource") DataSource dataSource) throws
            Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/**/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager moonsonDataSourceTransactionManager(
            @Qualifier("moonsonDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate moonsonSqlSessionTemplate(
            @Qualifier("moonsonSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Primary
    public TransactionTemplate moonsonTransactionTemplate(
            @Qualifier("moonsonDataSourceTransactionManager") DataSourceTransactionManager dataSourceTransactionManager) {
        TransactionTemplate bean = new TransactionTemplate();
        bean.setTransactionManager(dataSourceTransactionManager);
        return bean;
    }

}
