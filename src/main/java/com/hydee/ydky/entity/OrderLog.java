package com.hydee.ydky.entity;

import java.util.Date;

/**
 * 单据查询日志实体
 */
public class OrderLog {

    private Date iTime;
    private Date uTime;
    private String busno;
    private int type;

    public OrderLog() {
    }

    public OrderLog(Date iTime, Date uTime, String busno, int type) {
        this.iTime = iTime;
        this.uTime = uTime;
        this.busno = busno;
        this.type = type;
    }

    public Date getiTime() {
        return iTime;
    }

    public void setiTime(Date iTime) {
        this.iTime = iTime;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
