package com.hw.example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class HisUserRecord {
    /**
     * 
     * 对应字段 : ng_id
     */
    @Getter
    @Setter
    private Long ngId;

    /**
     * 用户id
     * 对应字段 : ng_user_id
     */
    @Getter
    @Setter
    private Long ngUserId;

    /**
     * 验证人员记录id
     * 对应字段 : ng_person_id
     */
    @Getter
    @Setter
    private String ngPersonId;

    /**
     * 验证时间
     * 对应字段 : ts_time
     */
    @Getter
    @Setter
    private Date tsTime;
}