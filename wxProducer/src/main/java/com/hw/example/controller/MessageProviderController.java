package com.hw.example.controller;

import com.hw.example.service.MessageProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/testMessageProvider")
public class MessageProviderController {

    @Autowired
    private MessageProviderService messageProviderService;

    @GetMapping("/test")
    public Map sendMQMessage() {
        return messageProviderService.getMessageProviderService();
    }
}