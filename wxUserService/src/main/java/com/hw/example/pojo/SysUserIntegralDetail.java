package com.hw.example.pojo;

public class SysUserIntegralDetail {
    /**
     * 
     * 对应字段 : id
     */
    private Long id;

    /**
     * 身份证号码
     * 对应字段 : card_id
     */
    private String cardId;

    /**
     * 积分消费
     * 对应字段 : integral_num
     */
    private Integer integralNum;

    /**
     * 积分类型
     * 对应字段 : integral_type
     */
    private Integer integralType;

    /**
     * 积分消费的明细
     * 对应字段 : integral_detail
     */
    private String integralDetail;

    /**
     * 积分消费状态（1：新增，2;删减）
     */
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public Integer getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(Integer integralNum) {
        this.integralNum = integralNum;
    }

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }

    public String getIntegralDetail() {
        return integralDetail;
    }

    public void setIntegralDetail(String integralDetail) {
        this.integralDetail = integralDetail == null ? null : integralDetail.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}