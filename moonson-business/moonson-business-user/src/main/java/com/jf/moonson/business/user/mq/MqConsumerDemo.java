package com.jf.moonson.business.user.mq;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

@Slf4j
public class MqConsumerDemo {

    @SneakyThrows
    public static void pushConsume(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("cg1");
        consumer.setNamesrvAddr("101.35.88.207:9876");
        // 订阅主题，可通过 subExpression 筛选特定的 tag
        consumer.subscribe("sample","*");
        // 创建监听器，收到消息则处理
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                list.forEach(m->{
                    log.info("消费数据：id={},body={}", m.getMsgId(), new String(m.getBody()));
                });
                // 返回状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者，与 broker 建立长连接
        consumer.start();

    }

    public static void main(String[] args) {
        pushConsume();

    }

}
