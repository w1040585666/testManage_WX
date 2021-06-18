package com.hw.example.dao;


import com.hw.example.pojo.HisUserRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HisUserRecordDao {
    /**
     * 根据主键删除数据库的记录
     *
     * @param ngId
     */
    int deleteByPrimaryKey(Long ngId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(HisUserRecord record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(HisUserRecord record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param ngId
     */
    HisUserRecord selectByPrimaryKey(Long ngId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(HisUserRecord record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(HisUserRecord record);
}