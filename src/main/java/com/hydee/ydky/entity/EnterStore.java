package com.hydee.ydky.entity;

/**
 * Created by niang on 2018/11/12.
 */
public class EnterStore {

    private String buson; //门店编码

    private String orgname; //门店名称

    private Integer status; //状态

    private String address; //门店地址

    private Double lon; //门店经度

    private Double lat; //门店纬度

    private String storeNum; //店长登陆账号

    private String storePassword; //店长登陆密码

    public String getBuson() {
        return buson;
    }

    public void setBuson(String buson) {
        this.buson = buson;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
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

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
    }
}
