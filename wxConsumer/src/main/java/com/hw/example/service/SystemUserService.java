package com.hw.example.service;


import com.hw.example.pojo.SystemUser;

/**
 * 调用公安系统接口Service层
 * @author wang xin.
 * @version 1.0
 * @date 2019/7/29 20:35.
 * @Copyright：2018 汉王智远科技有限公司 All rights reserved.
 */
public interface SystemUserService {

    /**
     * 根据用户名、密码验证身份
     * @param sz_username
     * @param sz_pwd
     * @return
     */
    SystemUser getSystemUserInfo(String sz_username, String sz_pwd);

    /**
     * 修改验证人员授权信息
     * @param systemUser
     */
    void updateByPrimaryKeySelective(SystemUser systemUser);
}
