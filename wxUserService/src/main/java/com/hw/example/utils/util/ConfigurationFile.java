package com.hw.example.utils.util;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Component;

/**
 * nacos配置中心类
 * @author wang xin.
 * @version 1.0
 * @date 2021/11/30 10:41.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */

@Component
public class ConfigurationFile {

    @NacosValue(value = "${faceDetectionSwitch: true}", autoRefreshed = true)
    public boolean faceDetectionSwitch;

    @NacosValue(value = "${faceDetectionUrl: 未配置URL}", autoRefreshed = true)
    public String faceDetectionUrl;

    @NacosValue(value = "${faceDetectionState: 1}", autoRefreshed = true)
    public  Integer faceDetectionState;
}
