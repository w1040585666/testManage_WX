package com.example.test.system;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangxin on 2020/12/1.
 */

@Api(tags = {"登录管理"})
@RestController
@RequestMapping("login")
public class Login {

    @PostMapping("loginOn")
    @ResponseBody
    public String userLogin(@RequestParam(required = false,value = "userName") String userName,
                                 @RequestParam(required = false,value = "pwd") String pwd) {
        System.out.println("哈哈哈哈");
        return "登录成功";
    }
}
