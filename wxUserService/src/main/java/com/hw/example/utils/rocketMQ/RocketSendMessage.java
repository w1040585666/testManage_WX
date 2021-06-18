package com.hw.example.utils.rocketMQ;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/05/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@Component
public class RocketSendMessage {
    private static Logger logger = LoggerFactory.getLogger(RocketSendMessage.class);

    @Autowired
    RocketMQProducer rocketMQProvider;

    /**
     * topic标识
     */
    @Value("${apache.rocketmq.producer.topic}")
    private String topic;

    /**
     *
     * @param Tag 类型
     * @param key 存储数据库key
     * @param context 存储记录的table
     * @return 提交mq的结果值
     */
    public boolean sendMessage(String Tag, String key, int flag, String context){
        boolean res = false;
        Message message;
        try {
            message = new Message(topic, Tag, key, flag, context.getBytes("UTF-8"), true);
            SendResult result = rocketMQProvider.producer.send(message);
            if(result.getSendStatus().compareTo(SendStatus.SEND_OK) == 0){
                res = true;
            }
            logger.info("MQProducer send message time = {},Tag = {} key = {} context = {}",System.currentTimeMillis(), Tag, key, context);
        }catch (Exception e){
            //mq发送异常处理
            logger.error("",e);
        }
        return res;
    }
}
