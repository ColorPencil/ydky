package com.hydee.ydky.entity;

public class MachineSellOrderDetail extends BaseMachineOrderDetail{
    private double salePrice;//商品零售价

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
