package com.xqxls.domain.order.service.calculatePromotion;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/14 19:08
 */
public enum CalcStrategy {

    SINGLE(1, "单品促销"),

    LADDER(3, "打折优惠"),

    FULL_REDUCTION(4, "满减"),

    NO_REDUCE(0, "无优惠");


    private Integer code;
    private String info;

    CalcStrategy(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
