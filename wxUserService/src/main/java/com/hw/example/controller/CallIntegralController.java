package com.hw.example.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.hw.example.utils.util.ConfigurationFile;
import com.hw.example.utils.util.RequestJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wang xin.
 * @version 1.0
 * @date 2021/6/16 17:22.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@RestController
@ResponseBody
@RequestMapping("/callIntegral")
public class CallIntegralController {

    /*@Autowired
    private CallIntegralService callIntegralService;*/

    @Autowired
    private ConfigurationFile configurationFile;

    private Integer visitUrl;

    @GetMapping("/getUserIntegralByCardId")
    public RequestJson getUserIntegralByCardId(@RequestParam String cardId) {
        System.out.println("配置1：" + configurationFile.faceDetectionSwitch);
        System.out.println("配置2：" + configurationFile.faceDetectionUrl);
        System.out.println("配置3：" + configurationFile.faceDetectionState);

        return null;
        //return callIntegralService.getUserIntegralByCardId(cardId);
    }

}