package com.imooc.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);


    @RabbitListener(queues=MQConfig.QUEUE)
    public void receive(String message){
        log.info("receiver messager: "+message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message){
        log.info("receiver queue1 messager: "+message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message){
        log.info("receiver queue2 messager: "+message);
    }

    @RabbitListener(queues=MQConfig.HEADERS_EXCHANGE)
    public void receiveHeaderQueue(byte[] message){
        log.info("receiver header queue messager: "+message);
    }
}
