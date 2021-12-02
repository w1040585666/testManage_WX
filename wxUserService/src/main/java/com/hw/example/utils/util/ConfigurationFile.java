package com.hw.example.utils.util;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * nacos配置中心类
 * @author wang xin.
 * @version 1.0
 * @date 2021/11/30 10:41.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */

public class ConfigurationFile {

    @Value(value = "${faceDetectionSwitch: true}")
    public boolean faceDetectionSwitch;

    @Value(value = "${faceDetectionUrl: 未配置URL}")
    public String faceDetectionUrl;

    @Value(value = "${faceDetectionState: 1}")
    public  Integer faceDetectionState;
}
