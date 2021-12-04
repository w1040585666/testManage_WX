package com.hw.example.fallbackFactory;

import com.hw.example.service.CallIntegralService;
import com.hw.example.utils.util.RequestJson;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wangxin on 2021/6/16.
 */
@Component
public class CallIntegralServiceHystric implements FallbackFactory<CallIntegralService>{
    @Override
    public CallIntegralService create(Throwable throwable){
        return new CallIntegralService(){
            @Override
            public RequestJson getUserIntegralByCardId(@RequestParam(value = "cardId") String cardId) {
                RequestJson result = new RequestJson();
                return RequestJson.failuerResult(result, "哎呦喂，服务器出现异常了！");
            }
        };
    }
}
