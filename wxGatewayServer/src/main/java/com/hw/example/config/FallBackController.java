package com.hw.example.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxin on 2021/12/3.
 */
@RestController
public class FallBackController {
    @GetMapping("/fallback")
    public String fallback() {
        return "Error:fallback";
    }
}
