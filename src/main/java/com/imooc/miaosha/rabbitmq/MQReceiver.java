package com.imooc.miaosha.rabbitmq;

import com.imooc.miaosha.domain.MiaoshaOrder;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaService;
import com.imooc.miaosha.service.OrderService;
import com.imooc.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;

//    @RabbitListener(queues=MQConfig.QUEUE)
//    public void receive(String message){
//        log.info("receiver messager: "+message);
//    }
//
//    @RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1(String message){
//        log.info("receiver queue1 messager: "+message);
//    }
//
//    @RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
//    public void receiveTopic2(String message){
//        log.info("receiver queue2 messager: "+message);
//    }
//
//    @RabbitListener(queues=MQConfig.HEADERS_EXCHANGE)
//    public void receiveHeaderQueue(byte[] message){
//        log.info("receiver header queue messager: "+message);
//    }

    @RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
    public void receive(String message){
        log.info("receiver messager: "+message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        if(goods.getStockCount() <= 0 ){
            return ;
        }

        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(order != null ){
            return ;
        }

        //减库存 下订单 写入秒杀订单
        //1.防止库存负数： 需要增加判断库存>0时，做减库存操作
        //2.防止同一个用户超卖：秒杀订单表增加唯一索引（user_id,godds_id)
        miaoshaService.miaosha(user,goods);

    }
}
