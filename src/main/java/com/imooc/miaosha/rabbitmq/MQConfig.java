package com.imooc.miaosha.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MQConfig {

    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchange";

    public static final String ROUTING_KEY1 = "topic.key1";
    public static final String ROUTING_KEY2 = "topic.#";

    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    public static final String HEADERS_EXCHANGE = "headersExchange";
    /**
     * DIRECT模式 交换机Exchange
     * 1.数据发送交换机
     * 2.交换机发送到QUEUE
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }

    /**
     * TOPIC模式 交换机Exchange
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }

    /**
     * TOPIC模式 交换机Exchange
     */
    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
    }

    /**
     * 支持通配符
     * @return
     */
    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
    }

    /**
     * Fanout模式 交换机Exchange  广播
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBindingg1(){
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingg2(){
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }

    /**s
     * Header模式 交换机Exchange
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Queue headerQueue1(){
        return new Queue(HEADERS_EXCHANGE,true);
    }

    @Bean
    public Binding headerBinding(){
        Map<String,Object> map = new HashMap<>();
        map.put("header1","v1");
        map.put("header2","v2");
        return BindingBuilder.bind(headerQueue1()).to(headersExchange()).whereAll(map).match();
    }
}
