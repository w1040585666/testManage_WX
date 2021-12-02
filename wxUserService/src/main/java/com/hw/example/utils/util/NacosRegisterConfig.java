//package com.hw.example.utils.util;
//
///**
// * 服务启动自动注册配置
// * @author wang xin.
// * @version 1.0
// * @date 2021/11/30 16:05.
// * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
// */
//
//import com.alibaba.nacos.api.annotation.NacosInjected;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
//public class NacosRegisterConfig {
//    @Value("${server.port}")
//    private int serverPort;
//
//    @Value("${spring.application.name}")
//    private String applicationName;
//
//    @NacosInjected
//    private NamingService namingService;
//
//    @PostConstruct
//    public void registerInstance() throws NacosException {
//        namingService.registerInstance(applicationName, "127.0.0.1", serverPort, "DEFAULT");
//    }
//
//}