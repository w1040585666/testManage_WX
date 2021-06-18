package com.hw.example.dao;

import com.hw.example.pojo.SysUserIntegral;
import com.hw.example.pojo.SysUserIntegralDetail;

public interface SysUserIntegralDao {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(SysUserIntegral record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(SysUserIntegral record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    SysUserIntegral selectByPrimaryKey(Long id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SysUserIntegral record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(SysUserIntegral record);

    SysUserIntegral getUserIntegralByCardId(String cardId);

    void updateUserIntegralByCardId(SysUserIntegralDetail sysUserIntegralDetail);
}