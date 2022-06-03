package com.jf.moonson.starter.redis.cache;

/**
 * @author LiuGong
 * @version $
 * @since 2020年05月21日 15:49
 */
public interface ServiceCacheManager {

    /**
     * 根据cachename与方法名清除缓存
     */
    void clearCache(Class cacheNameClass, String methodName);

    Boolean clearCache(String cacheName, String paramPart);
}
