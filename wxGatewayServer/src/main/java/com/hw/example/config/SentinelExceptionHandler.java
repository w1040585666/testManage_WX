package com.hw.example.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class SentinelExceptionHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
        CommonResponse<String> resp = new CommonResponse<>();
        int status = 200;
        //限流响应
        if (throwable instanceof FlowException) {
            resp.setCode(-1);
            resp.setMessage("提示：系统繁忙，请稍后再试");
            status = 429;
        }
        //服务降级响应
        else if (throwable instanceof DegradeException) {
            resp.setCode(-2);
            resp.setMessage("提示：系统繁忙，服务熔断降级处理");
        }
        //热点参数限流响应
        else if (throwable instanceof ParamFlowException) {
        }
        //触发系统保护规则响应
        else if (throwable instanceof SystemBlockException) {
        }
        //授权规则不通过响应
        else if (throwable instanceof AuthorityException) {
            resp.setCode(-5);
            resp.setMessage("提示：授权未通过");
            status = 511;
        }
        //返回固定响应信息
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(JSONObject.toJSONString(resp)));
    }
}
