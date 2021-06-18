package com.hw.example.service.impl;

import com.hw.example.dao.SysUserIntegralDao;
import com.hw.example.dao.SysUserIntegralDetailDao;
import com.hw.example.pojo.SysUserIntegral;
import com.hw.example.pojo.SysUserIntegralDetail;
import com.hw.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 人员信息Service层
 * @author wang xin.
 * @version 1.0
 * @date 2021/5/29 20:35.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserIntegralDao sysUserIntegralDao;

    @Autowired
    private SysUserIntegralDetailDao sysUserIntegralDetailDao;

    @Override
    public void initializeUserIntegral(SysUserIntegral sysUserIntegral) throws Exception {
        sysUserIntegralDao.insertSelective(sysUserIntegral);
    }

    @Override
    public SysUserIntegral getUserIntegralByCardId(String cardId) throws Exception {
        return sysUserIntegralDao.getUserIntegralByCardId(cardId);
    }

    @Override
    public void setUserIntegralDetail(SysUserIntegralDetail sysUserIntegralDetail) throws Exception {
        //录入积分明细
        sysUserIntegralDetailDao.insertSelective(sysUserIntegralDetail);
        //进行人员积分add or reduce操作
        sysUserIntegralDao.updateUserIntegralByCardId(sysUserIntegralDetail);
    }
}
