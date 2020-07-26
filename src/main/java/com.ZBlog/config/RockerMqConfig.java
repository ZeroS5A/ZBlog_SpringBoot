package com.ZBlog.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RockerMqConfig {
    //使用Value注解加载配置文件的配置
    @Value("${rocketmq.groupName}")
    private String groupName;
    @Value("${rocketmq.namesrvAddr}")
    private String nameserAddr;
    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;

    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;

    private DefaultMQProducer producer;


//    //生产者
//    @Bean
//    public DefaultMQProducer getRocketMQProducer() {
//
//        producer = new DefaultMQProducer(groupName);
//        producer.setNamesrvAddr(nameserAddr);
//        producer.setMaxMessageSize(maxMessageSize);
//        producer.setSendMsgTimeout(sendMsgTimeout);
//        producer.setVipChannelEnabled(false);
//
//        try {
//            producer.start();
//        } catch (MQClientException e) {
//            System.out.println("+++++++++请检查是否开启RocketMQ+++++++++++");
//            e.printStackTrace();
//        }
//        return producer;
//    }
//
//    //消费者
//    @Bean
//    public DefaultMQPushConsumer getRocketMQConsumer()
//    {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
//        consumer.setNamesrvAddr(nameserAddr);
//        consumer.setConsumeThreadMin(consumeThreadMin);
//        consumer.setConsumeThreadMax(consumeThreadMax);
//        consumer.setVipChannelEnabled(false);
//
//        //创建监听器
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(
//                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msg : msgs) {
//                    System.out.println("Message Received: " + new String(msg.getBody()));
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//
//        try {
//            consumer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//        return consumer;
//    }
}
