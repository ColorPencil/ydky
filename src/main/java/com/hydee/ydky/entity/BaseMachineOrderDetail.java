package com.hydee.ydky.entity;

public class BaseMachineOrderDetail {
    private String billno;//单据编号
    private String rowid;//行号
    private String wareid;//商品编码
    private int wareqty;//商品数量

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getWareid() {
        return wareid;
    }

    public void setWareid(String wareid) {
        this.wareid = wareid;
    }

    public int getWareqty() {
        return wareqty;
    }

    public void setWareqty(int wareqty) {
        this.wareqty = wareqty;
    }
}
