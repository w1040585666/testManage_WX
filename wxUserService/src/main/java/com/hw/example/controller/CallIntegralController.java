package com.hw.example.controller;

import com.hw.example.service.CallIntegralService;
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

    @Autowired
    private CallIntegralService callIntegralService;

    @GetMapping("/getUserIntegralByCardId")
    public RequestJson getUserIntegralByCardId(@RequestParam String cardId) {

        return callIntegralService.getUserIntegralByCardId(cardId);
    }
}