package com.hydee.ydky.entity;

import java.util.List;

/**
 * 订单实体
 */
public class Order {
    private String busno;//门店编码
    private String dcmno;//售药机编码
    private String billno;//单据编号
    private String status;//单据状态 0 未处理 -1失败 1成功
    private List<DetailBean> detail;//详情

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    private class DetailBean{
        private String billno;//单据编号
        private String rowid;//行号
        private String wareid;//商品编码
        private String wareqty;//商品数量
        private String purprice;//商品进价

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

        public String getWareqty() {
            return wareqty;
        }

        public void setWareqty(String wareqty) {
            this.wareqty = wareqty;
        }

        public String getPurprice() {
            return purprice;
        }

        public void setPurprice(String purprice) {
            this.purprice = purprice;
        }
    }
}
