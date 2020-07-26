package com.ZBlog.server.impl;

import com.ZBlog.commom.Result;
import com.ZBlog.server.RocketMqServer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RocketMqServerImpl implements RocketMqServer {

//    @Autowired
//    private DefaultMQProducer producer;

    @Override
    public Result sendMessage(String Message) throws Exception {
//        Message message = new Message("ZBlogChartRoom", "SendPub", "",Message.getBytes());
//        SendResult result = producer.send(message);
//        System.out.println(result.toString());
        return null;
    }
}
