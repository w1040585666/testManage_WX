package com.hw.example.dao;

import com.hw.example.pojo.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 调用公安系统接口DAO层
 * @author wang xin.
 * @version 1.0
 * @date 2019/7/29 20:38.
 * @Copyright：2018 汉王智远科技有限公司 All rights reserved.
 */
@Mapper
public interface SystemUserDao {

    /**
     * 根据用户名、密码验证身份
     * @param sz_username
     * @param sz_pwd
     * @return
     */
    SystemUser getSystemUserInfo(@Param("sz_username") String sz_username, @Param("sz_pwd") String sz_pwd);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SystemUser record);
}