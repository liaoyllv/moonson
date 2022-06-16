package com.jf.moonson.business.order.demo;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 参考：https://mp.weixin.qq.com/s?__biz=Mzg2NjE5NDQyOA==&mid=2247483768&idx=1&sn=1df06849222072ac87d1410aef969125&source=41#wechat_redirect
 */
@Slf4j
public class RateLimiterDemo {

    public static void testSmooth() {
        RateLimiter rateLimiter = RateLimiter.create(2);
        IntStream.range(0, 2).forEach(i -> {
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("end");

        });
    }

    public static void testSmoothWarmingUp() {
        // 创建一个平均分发令牌速率为2，预热期为3秒钟。由于设置了预热时间是3秒，令牌桶一开始并不会0.5秒发一个令牌，
        // 而是形成一个平滑线性下降的坡度，频率越来越高，在3秒钟之内达到原本设置的频率，以后就以固定的频率输出。
        // 这种功能适合系统刚启动需要一点时间来“热身”的场景。
        RateLimiter rateLimiter = RateLimiter.create(2, 3, TimeUnit.SECONDS);
        IntStream.range(0, 1).forEach(i -> {
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("get 1 token:{}s", rateLimiter.acquire(1));
            log.info("end");

        });
    }




    public static void main(String[] args) {
        // testSmooth();
        testSmoothWarmingUp();
    }
}
