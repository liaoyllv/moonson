package com.jf.moonson.starter.rocketmq;

/**
 * rocketmq 常量
 * 新增topic常量需要添加阿里云资源
 * 新增group常量需要添加阿里云资源
 * 新增tag常量不需要
 */
public interface RocketMQConstant {

    interface Topic {
        /**
         * 订单topic
         */
        String ORDER = "MOONSON_TOPIC_ORDER";

    }

    interface Group {

        /**
         * 默认group
         */
        String DEFAULT = "MOONSON_GROUP_DEFAULT";

        /**
         * 积分
         */
        String POINTS = "MOONSON_GROUP_POINTS";

    }

    interface Tag {

        /**
         * 下单
         */
        String ORDER_CREATE = "MOONSON_ORDER_CRATE";

        /**
         * 取消订单
         */
        String ORDER_CANCEL = "MOONSON_ORDER_CANCEL";

    }

}
