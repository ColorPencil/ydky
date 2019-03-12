package com.hydee.ydky.entity;

import java.util.Date;

public class StoreLog {

    private String busno;
    private String wareid;
    private Date lasttime;
    private String returnmess;

    public StoreLog(String busno, String wareid, Date lasttime, String returnmess) {
        this.busno = busno;
        this.wareid = wareid;
        this.lasttime = lasttime;
        this.returnmess = returnmess;
    }

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

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getReturnmess() {
        return returnmess;
    }

    public void setReturnmess(String returnmess) {
        this.returnmess = returnmess;
    }
}
