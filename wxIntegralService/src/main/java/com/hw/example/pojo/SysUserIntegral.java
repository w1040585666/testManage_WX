package com.hw.example.pojo;

import lombok.Getter;
import lombok.Setter;

public class SysUserIntegral {
    /**
     * 
     * 对应字段 : id
     */
    @Getter
    @Setter
    private Long id;

    /**
     * 真实姓名
     * 对应字段 : name
     */
    @Getter
    @Setter
    private String name;

    /**
     * 身份证号码
     * 对应字段 : card_id
     */
    @Getter
    @Setter
    private String cardId;

    /**
     * -1未知 0：女 1男
     * 对应字段 : gender
     */
    @Getter
    @Setter
    private Integer gender;

    /**
     * 电话
     * 对应字段 : phone
     */
    @Getter
    @Setter
    private String phone;

    /**
     * 积分
     * 对应字段 : integral
     */
    @Getter
    @Setter
    private Integer integral;
}