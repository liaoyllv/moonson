package com.jf.moonson.business.user.mq;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;

import lombok.RequiredArgsConstructor;

/**
 * demo
 *
 * @author 季风
 * @version $
 * @since 2022/6/22 15:54
 */
@RequiredArgsConstructor
@RocketMQTransactionListener
public class MqTransactionProducerDemo implements TransactionListener {


    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        return null;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return null;
    }
}
