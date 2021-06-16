package com.hw.example.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/05/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@EnableAutoConfiguration
@RestController
@ResponseBody
@RequestMapping("/messageProvider")
public class MessageProviderController {

    @GetMapping("/getMessage")
    public Map getMessage() {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("result", true);
        returnMap.put("message", "哈哈哈哈");
        return returnMap;
    }
}