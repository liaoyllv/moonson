package com.jf.moonson.business.user.mq;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

@Slf4j
public class MqProducerDemo {


    @SneakyThrows
    public static void main(String[] args) {
        // 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer("pg1");
        // 设置 nameServer
        producer.setNamesrvAddr("101.35.88.207:9876");
        producer.start();
        String data = "这是第%s条消息";
        IntStream.range(0, 10).forEach(index -> {
            Message message = new Message("sample", "test",
                    String.format(data, index).getBytes(StandardCharsets.UTF_8));
            try {
                Thread.sleep(1000);
                SendResult result = producer.send(message);
                log.info("消息已发送:msgId={},status={}", result.getMsgId(), result.getSendStatus());
            } catch (Exception e) {
                log.error("error,", e);
            }
        });
        producer.shutdown();
    }


}
