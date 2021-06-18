package com.hw.example.service.impl;

import com.hw.example.service.CallIntegralService;
import com.hw.example.utils.util.RequestJson;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxin on 2021/6/16.
 */
@Component
public class CallIntegralServiceHystric implements CallIntegralService {

    @Override
    public RequestJson getUserIntegralByCardId(@RequestParam(value = "name") String cardId) {
        RequestJson result = new RequestJson();
        return RequestJson.failuerResult(result, "哎，服务出现异常了！");
    }
}
