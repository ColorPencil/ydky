package com.hydee.ydky.entity;

import java.util.Date;

public class BaseMachineOrder {
    private String busno;//门店编码
    private String dcmno;//售药机编码
    private String billno;//单据编号
    private Date createTime;//单据创建时间，时间戳，海典web生成
    private Date lastTime;//单据最后修改时间，时间戳，海典web生成

    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getDcmno() {
        return dcmno;
    }

    public void setDcmno(String dcmno) {
        this.dcmno = dcmno;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
