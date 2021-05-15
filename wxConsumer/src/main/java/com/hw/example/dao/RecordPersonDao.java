package com.hw.example.dao;


import com.hw.example.pojo.RecordPerson;
import com.hw.example.pojo.SystemUserParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordPersonDao {
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
    int insert(RecordPerson record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(RecordPerson record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param ngId
     */
    RecordPerson selectByPrimaryKey(Long ngId);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(RecordPerson record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(RecordPerson record);

    /**
     * 根据身份证号来查询数据库
     *
     * @param systemUserParam
     */
    RecordPerson queryByCardNo(SystemUserParam systemUserParam);
}