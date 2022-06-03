package com.jf.moonson.starter.redis.cache;

import cn.hutool.json.JSONUtil;
import com.jf.moonson.starter.redis.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @author LiuGong
 * @version $
 * @since 2020年05月21日 15:54
 */
@Slf4j
class ServiceCacheManagerImpl implements ServiceCacheManager {

    private final CacheProperties cacheProperties;
    private final RedisHelper redisHelper;
    private final RedisTemplate<String, Object> redisTemplate;

    ServiceCacheManagerImpl(CacheProperties cacheProperties, RedisHelper redisHelper, RedisTemplate<String, Object> redisTemplate) {
        this.cacheProperties = cacheProperties;
        this.redisHelper = redisHelper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void clearCache(Class cacheNameClass, String methodName) {
        String keyPrefix = cacheProperties.getRedis().getKeyPrefix();
        String simpleName = cacheNameClass.getSimpleName();
        String pattern = keyPrefix + simpleName + ":" + methodName + ":*";
        log.info("clearCache key={}", pattern);
        Set<String> keys = redisHelper.keys(pattern);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
        log.info("clearCache keys={}", JSONUtil.toJsonStr(keys));
    }

    @Override
    public Boolean clearCache(String cacheName, String paramPart) {
        String keyPrefix = cacheProperties.getRedis().getKeyPrefix();
        return redisTemplate.delete(keyPrefix + cacheName + ":" + paramPart);

    }
}
