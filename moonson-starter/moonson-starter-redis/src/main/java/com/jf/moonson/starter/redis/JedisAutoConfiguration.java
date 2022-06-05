package com.jf.moonson.starter.redis;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 */
@Slf4j
@Configuration
public class JedisAutoConfiguration {

    public JedisAutoConfiguration() {
        log.info("Initializing JedisAutoConfiguration");
    }

    // @Bean("sessionRedisProperties")
    // @ConfigurationProperties(prefix = "spring.session-redis")
    // public RedisProperties sessionRedisProperties() {
    //     return createRedisProperties();
    // }
    //
    // @SpringSessionRedisConnectionFactory
    // @Bean
    // public JedisConnectionFactory sessionConnectionFactory(
    //         @Qualifier("sessionRedisProperties") RedisProperties properties) {
    //     return createJedisConnectionFactory(properties);
    // }

    /****************************************************************************************************************/

    @Bean("redisProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisProperties redisProperties() {
        return createRedisProperties();
    }

    @Bean
    @Primary
    public JedisConnectionFactory redisConnectionFactory(@Qualifier("redisProperties") RedisProperties properties) {
        return createJedisConnectionFactory(properties);
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> objectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(createObjectMapper());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisHelper redisHelper(RedisTemplate<String, Object> objectRedisTemplate) {
        return new RedisHelper(objectRedisTemplate);
    }

    @Bean
    @Primary
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(createObjectMapper());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @Primary
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return createStringRedisTemplate(redisConnectionFactory);
    }

    /****************************************************************************************************************/

    private StringRedisTemplate createStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    private JedisConnectionFactory createJedisConnectionFactory(RedisProperties properties) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(properties.getHost(),
                properties.getPort());
        configuration.setPassword(RedisPassword.of(properties.getPassword()));
        JedisPoolConfig poolConfig = createJedisPoolConfig(properties.getJedis().getPool());
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling()
                .poolConfig(poolConfig).build();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration,
                jedisClientConfiguration);
        return jedisConnectionFactory;
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        timeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        timeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        timeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));

        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(timeModule);

        return om;
    }

    private RedisProperties createRedisProperties() {
        return new RedisProperties();
    }

    private JedisPoolConfig createJedisPoolConfig(Pool pool) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        config.setMaxWaitMillis(100000L);
        if (pool.getTimeBetweenEvictionRuns() != null) {
            config.setTimeBetweenEvictionRunsMillis(pool.getTimeBetweenEvictionRuns().toMillis());
        }
        if (pool.getMaxWait() != null) {
            config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }
        return config;
    }
}
