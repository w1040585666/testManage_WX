package com.hw.example.service.impl;

import com.hw.example.dao.SystemUserDao;
import com.hw.example.pojo.SystemUser;
import com.hw.example.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 调用公安系统接口实现类
 * @author wang xin.
 * @version 1.0
 * @date 2019/7/29 20:38.
 * @Copyright：2018 汉王智远科技有限公司 All rights reserved.
 */
@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserDao systemUserDao;

    /**
     * 根据用户名、密码验证身份
     * @param sz_username
     * @param sz_pwd
     * @return
     */
    @Override
    @Transactional
    public SystemUser getSystemUserInfo(String sz_username, String sz_pwd){
        SystemUser user = systemUserDao.getSystemUserInfo(sz_username, sz_pwd);
        return user;
    }

    /**
     * 修改验证人员授权信息
     * @param systemUser
     */
    @Override
    @Transactional
    public void updateByPrimaryKeySelective(SystemUser systemUser){
        systemUserDao.updateByPrimaryKeySelective(systemUser);
    }
}
