package com.hydee.ydky.entity;

public class Stock {

    private String busno;//门店编码
    private String wareid;//商品编码
    private double wareqty;//商品数量

    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getWareid() {
        return wareid;
    }

    public void setWareid(String wareid) {
        this.wareid = wareid;
    }

    public double getWareqty() {
        return wareqty;
    }

    public void setWareqty(double wareqty) {
        this.wareqty = wareqty;
    }
}
