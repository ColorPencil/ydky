package com.hydee.ydky.entity;

import java.util.List;

public class MachineSellOrder extends BaseMachineOrder{
    private List<MachineSellOrderDetail> detail;//详情

    public List<MachineSellOrderDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<MachineSellOrderDetail> detail) {
        this.detail = detail;
    }
}
