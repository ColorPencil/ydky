package com.hydee.ydky.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单实体
 */
public class MachineOrder extends BaseMachineOrder{
    private int status;//单据状态 0 未处理 -1失败 1成功
    private List<MachineOrderDetail> detail;//详情

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MachineOrderDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<MachineOrderDetail> detail) {
        this.detail = detail;
    }
}
