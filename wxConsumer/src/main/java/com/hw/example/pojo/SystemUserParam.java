package com.hw.example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class SystemUserParam implements Serializable {

    /**
     * 用户名
     */
    @Getter
    @Setter
    private String userName;

    /**
     * 密码
     */
    @Getter
    @Setter
    private String pwd;

    /**
     * 身份证号
     */
    @Getter
    @Setter
    private String cardNo;

    /**
     * 检查类型
     */
    /*
        1：身份核验(ID+name)
        2：人脸身份核验(ID+name+photo)
        3：ID关注级别(ID)
        4：身份核验+ID关注级别(ID+name)
        5：人脸身份核验+ID关注级别
        (ID+name+photo)
        6：人脸识别+关注级别
        (photo)
    * */
    @Getter
    @Setter
    private String checkType;
    /**
     * 证件照
     */
    @Getter
    @Setter
    private String cardBase64;
    /**
     * 抓拍人脸照
     */
    @Getter
    @Setter
    private String faceBase64;

    /**
     * 姓名
     */
    @Getter
    @Setter
    private String name;

    /**
     * 身份证有效期起始日期
     */
    @Getter
    @Setter
    private String YXQQSRQ;

    /**
     * 身份证有效期截止日期
     */
    @Getter
    @Setter
    private String YXQJZRQ;

    /**
     * 性别
     */
    @Getter
    @Setter
    private String sex;

    /**
     * 民族
     */
    @Getter
    @Setter
    private String nation;
}