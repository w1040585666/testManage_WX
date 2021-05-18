package com.hw.example.utils.rocketMQ;

import com.hw.example.pojo.MQCommandRecord;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/05/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@Component
public class RocketMQConsumer {

    private static Logger logger = LoggerFactory.getLogger(RocketMQConsumer.class);

    /**
     * 消费者的组名
     */
    @Value("${apache.rocketmq.consume.consumeGroup}")
    private String consumerGroup;

    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    /**
     * topic标识
     */
    @Value("${apache.rocketmq.consume.topic}")
    private String topic;

    private DefaultMQPushConsumer consumer;

    /**
     * @PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
     */
    @PostConstruct
    public void defaultMQPushConsumer() {
        //消费者的组名
        consumer = new DefaultMQPushConsumer(consumerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe(topic, "*");
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            //如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                try {
                    for (MessageExt messageExt : list) {
                        String paramContext = new String(messageExt.getBody(), "UTF-8");
                        MQCommandRecord mqCommandRecord = new MQCommandRecord();
                        mqCommandRecord.setTag(messageExt.getTags());
                        mqCommandRecord.setKey(messageExt.getKeys());
                        mqCommandRecord.setFlag(messageExt.getFlag());
                        mqCommandRecord.setContext(new String(messageExt.getBody()));
                        logger.info("MQ consume time = {} , {}",System.currentTimeMillis(), mqCommandRecord.getContext());
                    }
                } catch (Exception e) {
                    logger.error("",e);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            });
            consumer.start();
            logger.info(" defaultMQPushConsumer start ");
            logger.info("deviceCommandToPool  start ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closeDefaultMQPushConsumer(){
        consumer.shutdown();
        logger.info("defaultMQPushConsumer shutdown");
    }
}
