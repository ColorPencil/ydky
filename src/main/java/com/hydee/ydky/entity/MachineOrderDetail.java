package com.hydee.ydky.entity;

public class MachineOrderDetail extends BaseMachineOrderDetail{
    private double purprice;//商品进价
    private String type;//1: 补货 2: 退货

    public double getPurprice() {
        return purprice;
    }

    public void setPurprice(double purprice) {
        this.purprice = purprice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
