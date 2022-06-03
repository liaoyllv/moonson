package com.jf.moonson.starter.redis.clusterseq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author LiuGong
 * @version $
 * @since 2020年01月03日 11:50
 */
@Slf4j
@Configuration
public class ClusterSeqAutoConfiguration {

    @Value("${spring.profiles.active:test}")
    private String env;

    public ClusterSeqAutoConfiguration() {
        log.info("Initializing ClusterSeqAutoConfiguration");
    }

    @Bean
    @Autowired
    ClusterSeqGenerator clusterSeqGenerator(StringRedisTemplate stringRedisTemplate) {
        if ("test".equals(env)||"test2".equals(env)||"dev".equals(env)) {
            env = "test";
        }
        ClusterSeqGenerator clusterSeqGenerator = new ClusterSeqGeneratorRedisImpl(stringRedisTemplate, env);
        return clusterSeqGenerator;
    }
}
