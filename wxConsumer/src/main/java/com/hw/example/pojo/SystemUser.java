package com.hw.example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class SystemUser implements Serializable {
    /**
     * 
     * 对应字段 : ng_id
     */
    @Getter
    @Setter
    private Long ng_id;

    /**
     * 用户名
     * 对应字段 : sz_username
     */
    @Getter
    @Setter
    private String sz_username;

    /**
     * 密码
     * 对应字段 : sz_pwd
     */
    @Getter
    @Setter
    private String sz_pwd;

    /**
     * 授权数量
     * 对应字段 : nt_count
     */
    @Getter
    @Setter
    private Integer nt_count;

    /**
     * 剩余数量
     * 对应字段 : nt_count_remain
     */
    @Getter
    @Setter
    private Integer nt_count_remain;

}