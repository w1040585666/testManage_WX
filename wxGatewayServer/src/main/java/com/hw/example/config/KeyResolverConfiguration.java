package com.hw.example.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 限流规则配置类
 * @author wang xin.
 * @version 1.0
 * @date 2021/12/3 11:47.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@Configuration
public class KeyResolverConfiguration{

    @Bean
    public KeyResolver ipKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                //获取访问者的ip地址, 通过访问者ip地址进行限流, 限流使用的是Redis中的令牌桶算法
                String hostName = exchange.getRequest().getRemoteAddress().getHostName();
                return Mono.just(hostName);
            }
        };
    }
}
