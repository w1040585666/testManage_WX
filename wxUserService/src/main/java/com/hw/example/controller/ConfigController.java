package com.hw.example.controller;

import com.hw.example.utils.util.RequestJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * Nacos配置中心类
 * @author wang xin.
 * @version 1.0
 * @date 2021/12/1 17:16.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@RestController
@ResponseBody
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value(value = "${faceDetectionSwitch: true}")
    public boolean faceDetectionSwitch;

    @Value(value = "${faceDetectionUrl: 未配置URL}")
    public String faceDetectionUrl;

    @Value(value = "${faceDetectionState: 1}")
    public Integer faceDetectionState;

    @Value(value = "${test.testUrl: 1}")
    public String testUrl;

    @Value(value = "${test.testUserName: 1}")
    public String testUserName;

    @Value(value = "${test.testPas: 1}")
    public String testPas;

    @GetMapping("/getConfigInfo")
    public RequestJson getConfigInfo() {
        System.out.println("配置1：" + faceDetectionSwitch);
        System.out.println("配置2：" + faceDetectionUrl);
        System.out.println("配置3：" + faceDetectionState);

        System.out.println("配置4：" + testUrl);
        System.out.println("配置5：" + testUserName);
        System.out.println("配置6：" + testPas);
        return null;
    }
}