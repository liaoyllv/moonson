package com.jf.moonson.starter.redis.clusterseq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * id按长度18设计，年月日时分（12位）+ {@link #SEQ_SIZE}位自增序列，每次自增在{@link #RANDOM_NEXT_OFFSET}内随机。
 * <br/>
 * 一分钟内最少可生成的序列数= (10<sup>{@link #SEQ_SIZE}</sup> -1) / {@link #RANDOM_NEXT_OFFSET}
 * <br/>
 * 如果一分钟内{@link #SEQ_SIZE}位的自增序列用完，则等待下一秒再生成
 */
@Slf4j
class ClusterSeqGeneratorRedisImpl implements ClusterSeqGenerator {

    private final int RANDOM_NEXT_OFFSET = 600;

    private final int SEQ_SIZE = 7;

    private final Long MAX_SEQ_WITH_PAD = Double.valueOf(Math.pow(10, SEQ_SIZE)).longValue() - 1;

    private final String FORMAT_PAD = "%0" + SEQ_SIZE + "d";

    public static final String KEY_PREFIX = "moo:tax:";

    final StringRedisTemplate stringRedisTemplate;
    final String env;

    ClusterSeqGeneratorRedisImpl(StringRedisTemplate stringRedisTemplate, String env) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.env = env;
    }

    @Override
    public Long getSeqId(ClusterSeqBizTypeEnum seqBizType) {
        String minute = getMinute();
        String key = KEY_PREFIX + seqBizType.toString() + minute;
        final Long increment = stringRedisTemplate.boundValueOps(key)
                .increment(ThreadLocalRandom.current().nextLong(1, RANDOM_NEXT_OFFSET));
        stringRedisTemplate.expire(key, 120, TimeUnit.SECONDS);
        if (increment < MAX_SEQ_WITH_PAD) {
            return Long.valueOf(getPrefix(env) + minute + String.format(FORMAT_PAD, increment));
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("getSeqId sleep error", e);
            }
            return getSeqId(seqBizType);
        }

    }

    private static String getMinute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        return LocalDateTime.now().format(formatter);
    }

    private int getPrefix(String env) {
        if ("prod".equals(env)) {
            return ClusterSeqGenerator.PROD_PREFIX;
        } else if ("pre".equals(env)) {
            return ClusterSeqGenerator.PRE_PREFIX;
        } else {
            return ClusterSeqGenerator.TEST_PREFIX;
        }
    }
}
