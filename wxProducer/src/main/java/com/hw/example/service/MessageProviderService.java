package com.hw.example.service;

import com.hw.example.service.impl.MessageProviderHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 消息提供者接口,通过feign调用
 */
@FeignClient(value = "wx-messageProvider",fallback = MessageProviderHystric.class)
public interface MessageProviderService {

    @RequestMapping(value = "/messageProvider/getMessage",method = RequestMethod.GET)
    Map<String, Object> getMessageProviderService();
}
