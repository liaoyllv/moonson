package com.jf.moonson.starter.redis.clusterseq;

/**
 * 集群序号
 */
public interface ClusterSeqGenerator {
    Integer PROD_PREFIX = 50;
    Integer PRE_PREFIX = 40;
    Integer TEST_PREFIX = 30;

    Long getSeqId(ClusterSeqBizTypeEnum seqBizType);

}
