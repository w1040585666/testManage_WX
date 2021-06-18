package com.hw.example.controller;

import com.hw.example.pojo.SysUserIntegral;
import com.hw.example.pojo.SysUserIntegralDetail;
import com.hw.example.service.SysUserService;
import com.hw.example.utils.RequestJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户积分服务Controller
 * @author wxin.
 * @version 1.0.0.0
 * @date 2021/01/18.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@RestController
@ResponseBody
@RequestMapping("/integralService")
public class IntegralServiceController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 创建人员时，初始化人员积分
     * @author wang xin.
     * @date 2021/2/18 10:53.
     * @param sysUserIntegral:人员积分实体
     * @return RequestJson
     */
    @PostMapping("/initializeUserIntegral")
    public RequestJson initializeUserIntegral(@RequestBody SysUserIntegral sysUserIntegral) {
        RequestJson result = new RequestJson();
        try {
            sysUserService.initializeUserIntegral(sysUserIntegral);
            result = RequestJson.successResult(result, "人员初始化完成");
        } catch (Exception e) {
            result = RequestJson.failuerResult(result, "异常");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据CardId获取人员积分
     * @author wang xin.
     * @date 2021/2/18 11:00.
     * @param cardId        身份证号
     * @return java.util.Map.
     */
    @GetMapping("/getUserIntegralByCardId")
    public RequestJson getUserIntegralByCardId(@RequestParam String cardId) {
        RequestJson result = new RequestJson();
        SysUserIntegral info = null;
        try {
            info = sysUserService.getUserIntegralByCardId(cardId);
            result = RequestJson.successResult(result, info, "操作成功");
        } catch (Exception e) {
            result = RequestJson.failuerResult(result, "异常");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 积分明细
     * @author wang xin.
     * @date 2021/2/18 10:53.
     * @param sysUserIntegralDetail:积分明细
     * @return RequestJson
     */
    @PostMapping("/setUserIntegralDetail")
    public RequestJson setUserIntegralDetail(@RequestBody SysUserIntegralDetail sysUserIntegralDetail) {
        RequestJson result = new RequestJson();
        try {
            if(sysUserIntegralDetail == null || sysUserIntegralDetail.getCardId() == null || sysUserIntegralDetail.getState() == null || sysUserIntegralDetail.getIntegralNum() == null){
                result = RequestJson.failuerResult(result, "参数缺失，请确认");
            }
            sysUserService.setUserIntegralDetail(sysUserIntegralDetail);
            result = RequestJson.successResult(result, "录入积分明细完成");
        } catch (Exception e) {
            result = RequestJson.failuerResult(result, "异常");
            e.printStackTrace();
        }
        return result;
    }
}