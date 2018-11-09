package com.hydee.ydky.utils;

public enum Enums {
    //单据拉取日志表
    ORDER_LOG_TYPE_APPLY(0, "D_DCM_APPLY"),//补货
    ORDER_LOG_TYPE_RETURN(1, "D_DCM_RETURN"),//退货
    ORDER_LOG_TYPE_SELL(2, "D_DCM_SALE");//零售

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    Enums(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
