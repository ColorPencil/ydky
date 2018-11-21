package com.hydee.ydky.entity;

/**
 * Created by niang on 2018/11/12.
 */
public class EnterMachine {
    private String busno;
    private String dcmno;
    private Integer status;
    private String address;
    private Double lon;
    private Double lat;
    private Integer localCount;
    private String introduce;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getLocalCount() {
        return localCount;
    }

    public void setLocalCount(Integer localCount) {
        this.localCount = localCount;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
