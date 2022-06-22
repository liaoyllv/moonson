package com.jf.moonson.business.user.mq;

import java.util.Set;

import org.apache.rocketmq.client.consumer.MessageQueueListener;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;

import com.jf.moonson.starter.rocketmq.RocketMQConstant.Group;
import com.jf.moonson.starter.rocketmq.RocketMQConstant.Tag;
import com.jf.moonson.starter.rocketmq.RocketMQConstant.Topic;

/**
 * demo
 *
 * @author 季风
 * @version $
 * @since 2022/6/22 15:54
 */
@RocketMQMessageListener(topic = Topic.DEFAULT, consumerGroup = Group.DEFAULT, selectorExpression = Tag.MOONSON_USER_VIEW, consumeMode = ConsumeMode.ORDERLY)
public class MqListenerDemo implements MessageQueueListener {


    @Override
    public void messageQueueChanged(String s, Set<MessageQueue> set, Set<MessageQueue> set1) {

    }
}
