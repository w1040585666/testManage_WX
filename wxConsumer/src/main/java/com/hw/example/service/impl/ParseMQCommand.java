package com.hw.example.service.impl;

import com.hw.example.pojo.MQCommandRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;

/**
 * 公共方法
 * 接受生产组发送的MQ消息
 * @author wang xin.
 * @version 1.0
 * @date 2021/05/18 11:08.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@Component
public class ParseMQCommand extends Thread {

    private static Logger logger = LoggerFactory.getLogger(ParseMQCommand.class);

    private ArrayDeque<MQCommandRecord> adqs = new ArrayDeque<>();

    private volatile boolean isWait = false;


    @Override
    public void run() {
        for(;;){
            if(adqs.isEmpty()){
                synchronized (ParseMQCommand.class){
                    try {
                        isWait = true;
                        ParseMQCommand.class.wait();
                    } catch (InterruptedException e) {
                        logger.error("",e);
                    }
                }
            }else{
                try {
                    MQCommandRecord mqCommandRecord = adqs.poll();
                    //具体业务处理
                } catch (Exception e) {
                    logger.error("",e);
                }
            }
        }
    }

    public void addMQCommandRecord(MQCommandRecord mqCommandRecord){
        adqs.addLast(mqCommandRecord);
        if(isWait){
            synchronized(ParseMQCommand.class){
                ParseMQCommand.class.notifyAll();
            }
        }
    }
}
