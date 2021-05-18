package com.hw.example.utils.rocketMQ;

import com.hw.example.pojo.MQCommandRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/05/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@Component
public class RocketSendEntranceToMessage {

    private static Logger logger = LoggerFactory.getLogger(RocketSendEntranceToMessage.class);

    /**
     * MQ消息发送类
     */
    @Autowired
    private RocketSendMessage rocketSendMessage;

    /**
     * MQ向外暴漏的总入口
     * @author wang xin.
     * @date 2021/05/18.
     * @param flag, commandRecords
     * @return void.
     */
    public void sendMqDisposeParam(String tag, String key, int flag, String context){
        //封装参数到POJO,调用发送MQ方法
        MQCommandRecord mqCommandRecord = new MQCommandRecord();
        try {
            mqCommandRecord.setTag(tag);//tag
            mqCommandRecord.setKey(key);//类型
            mqCommandRecord.setFlag(flag);//flag标识码
            mqCommandRecord.setContext(context);//备注
            sendMqToManage(mqCommandRecord);
        }catch (Exception e) {
            logger.error("",e);
        }
    }

    /**
     * MQ发送命令总入口
     * @author wang xin.
     * @date 2021/05/18.
     * @param mqCommandRecord
     * @return void.
     */
    private void sendMqToManage(MQCommandRecord mqCommandRecord){
        try {
            //发送Mq到应用层
            rocketSendMessage.sendMessage(mqCommandRecord.getTag(), mqCommandRecord.getKey(), mqCommandRecord.getFlag(), mqCommandRecord.getContext());
        }catch (Exception e) {
            logger.error("",e);
        }
    }
}
