package com.hydee.ydky.entity;

import java.util.List;

public class MachineSellOrder extends BaseMachineOrder{
    private int status;//单据状态 0 未处理 -1失败 1成功
    private List<MachineSellOrderDetail> detail;//详情

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MachineSellOrderDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<MachineSellOrderDetail> detail) {
        this.detail = detail;
    }
}
