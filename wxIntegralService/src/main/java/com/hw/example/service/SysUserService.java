package com.hw.example.service;

import com.hw.example.pojo.SysUserIntegral;
import com.hw.example.pojo.SysUserIntegralDetail;

/**
 * 人员信息Service层
 * @author wang xin.
 * @version 1.0
 * @date 2021/5/29 20:35.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
public interface SysUserService {

    void initializeUserIntegral(SysUserIntegral sysUserIntegral) throws Exception;

    SysUserIntegral getUserIntegralByCardId(String cardId) throws Exception;

    void setUserIntegralDetail(SysUserIntegralDetail sysUserIntegralDetail) throws Exception;
}
