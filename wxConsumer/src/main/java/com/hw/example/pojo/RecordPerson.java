package com.hw.example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class RecordPerson {
    /**
     * 
     * 对应字段 : ng_id
     */
    @Getter
    @Setter
    private Long ngId;

    /**
     * 身份证号
     * 对应字段 : sz_card_id
     */
    @Getter
    @Setter
    private String szCardId;

    /**
     * 姓名
     * 对应字段 : sz_name
     */
    @Getter
    @Setter
    private String szName;

    /**
     * 验证方式
     * 对应字段 : nt_checktype
     */
    @Getter
    @Setter
    private String ntChecktype;

    /**
     * 身份证照片
     * 对应字段 : ba_card_picture
     */
    @Getter
    @Setter
    private String baCardPicture;

    /**
     * 实时照片
     * 对应字段 : ba_picture
     */
    @Getter
    @Setter
    private String baPicture;

    /**
     * 性别
     * 对应字段 : sz_sex
     */
    @Getter
    @Setter
    private String szSex;

    /**
     * 民族
     * 对应字段 : sz_nation
     */
    @Getter
    @Setter
    private String szNation;

    /**
     * 身份证有效起始日期
     * 对应字段 : ts_card_begin
     */
    @Getter
    @Setter
    private Date tsCardBegin;

    /**
     * 身份证有效结束日期
     * 对应字段 : ts_card_end
     */
    @Getter
    @Setter
    private Date tsCardEnd;

    /**
     * 照片比对相似度：0--100
     * 对应字段 : nt_score
     */
    @Getter
    @Setter
    private Integer ntScore;

    /**
     * 关注级别：0：正常人员1：非正常人员，但不在关注级别里A，B:关注级别，用“，”分隔
     * 对应字段 : sz_black_level
     */
    @Getter
    @Setter
    private String szBlackLevel;

    /**
     * 创建时间
     * 对应字段 : ts_time
     */
    @Getter
    @Setter
    private Date tsTime;

}