package com.hydee.ydky.entity;

public class Stock {

    private String busNo;//门店编码
    private String wareId;//商品编码
    private double wareQty;//商品数量
    private int sort;//类型

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public double getWareQty() {
        return wareQty;
    }

    public void setWareQty(double wareQty) {
        this.wareQty = wareQty;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
