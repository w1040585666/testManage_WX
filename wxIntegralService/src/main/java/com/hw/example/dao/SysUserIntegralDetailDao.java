package com.hw.example.dao;

import com.hw.example.pojo.SysUserIntegralDetail;

public interface SysUserIntegralDetailDao {
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
    int insert(SysUserIntegralDetail record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(SysUserIntegralDetail record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    SysUserIntegralDetail selectByPrimaryKey(Long id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SysUserIntegralDetail record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(SysUserIntegralDetail record);
}