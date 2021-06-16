package com.hw.example.service.impl;

import com.hw.example.service.MessageProviderService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxin on 2021/6/16.
 */
@Component
public class MessageProviderHystric implements MessageProviderService {

    @Override
    public Map<String, Object> getMessageProviderService() {
        Map<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("message", "哎，服务出现异常了！");
        return map;
    }
}
