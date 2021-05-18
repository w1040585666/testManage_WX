package com.hw.example.controller;

import com.alibaba.fastjson.JSON;
import com.hw.example.utils.redis.RedisClient;
import com.hw.example.utils.rocketMQ.RocketSendEntranceToMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/05/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@EnableAutoConfiguration
@RestController
@ResponseBody
@RequestMapping("/wxTest")
public class WXTestController {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RocketSendEntranceToMessage rocketSendEntranceToMessage;

    @GetMapping("/sendMQMessage")
    public Map sendMQMessage() {
        Map<String, Object> returnMap = new HashMap<>();
        //声明发送的MQ消息
        rocketSendEntranceToMessage.sendMqDisposeParam("123456789", "5", 1, JSON.toJSONString("我只是测试发送的MQ消息，嘿嘿"));

        return returnMap;
    }
}