package com.hydee.ydky.service;

import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.OrderLog;

import java.util.Date;
import java.util.List;

public interface APIMachineService {

    OrderLog selectLastTime(String busno, int type);

    int insertLastTime(String busno, Date timestamp, int type);

    int apply(MachineOrder order);

    int dcmReturn(MachineOrder order);

    int sell(MachineSellOrder order);

    List<MachineOrder> selectApplyOrderByTime(String busno, Date timestamp);
            ;
    List<MachineOrder> selectReturnOrderByTime(String busno, Date timestamp);

    List<MachineSellOrder> selectSellOrderByTime(String busno, Date timestamp);

    int updateApplyOrder(List<MachineOrder> orders);

    int updateReturnOrder(List<MachineOrder> orders);
}
