package com.hydee.ydky.service;

import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.OrderLog;

import java.util.Date;
import java.util.List;

public interface APIMachineService {

    OrderLog selectLastTime(int type);

    int insertLastTime(Date timestamp, int type);

    int apply(MachineOrder order);

    int dcmReturn(MachineOrder order);

    int sell(MachineSellOrder order);

    List<MachineOrder> selectApplyOrderByTime(Date timestamp);
            ;
    List<MachineOrder> selectReturnOrderByTime(Date timestamp);

    List<MachineSellOrder> selectSellOrderByTime(Date timestamp);

    int updateApplyOrder(List<MachineOrder> orders);

    int updateReturnOrder(List<MachineOrder> orders);
}
