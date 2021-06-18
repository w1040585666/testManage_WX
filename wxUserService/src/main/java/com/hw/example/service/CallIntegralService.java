package com.hw.example.service;

import com.hw.example.service.impl.CallIntegralServiceHystric;
import com.hw.example.utils.util.RequestJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 消息提供者接口,通过feign调用
 */
@FeignClient(value = "wx-integralService",fallback = CallIntegralServiceHystric.class)
public interface CallIntegralService {

    @RequestMapping(value = "/integralService/getUserIntegralByCardId",method = RequestMethod.GET)
    RequestJson getUserIntegralByCardId(@RequestParam(value = "cardId") String cardId);
}
