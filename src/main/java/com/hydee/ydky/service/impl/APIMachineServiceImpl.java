package com.hydee.ydky.service.impl;

import com.hydee.ydky.dao.MachineMapper;
import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.OrderLog;
import com.hydee.ydky.service.APIMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class APIMachineServiceImpl implements APIMachineService {

    @Autowired
    MachineMapper machineMapper;

    @Override
    public OrderLog selectLastTime(int type) {
        return machineMapper.selectLastTime(type);
    }

    @Override
    public int insertLastTime(Date timestamp, int type) {
        int commit;
        OrderLog orderLog = machineMapper.selectLastTime(type);
        if(orderLog == null){
            orderLog = new OrderLog(timestamp, timestamp, type);
            commit = machineMapper.insertLastTime(orderLog);
        }else{
            orderLog.setuTime(timestamp);
            commit = machineMapper.updateLastTime(orderLog);
        }
        return commit;
    }

    @Override
    public int apply(MachineOrder order) {
        return machineMapper.insertApplyOrder(order);
    }

    @Override
    public int dcmReturn(MachineOrder order) {
        return machineMapper.insertReturnOrder(order);
    }

    @Override
    public int sell(MachineSellOrder order) {
        return machineMapper.insertSellOrder(order);
    }

    @Override
    public List<MachineOrder> selectApplyOrderByTime(Date timestamp) {
        return machineMapper.selectApplyOrderByTime(timestamp);
    }

    @Override
    public List<MachineOrder> selectReturnOrderByTime(Date timestamp) {
        return machineMapper.selectReturnOrderByTime(timestamp);
    }

    @Override
    public List<MachineSellOrder> selectSellOrderByTime(Date timestamp) {
        return machineMapper.selectSellOrderByTime(timestamp);
    }

    @Override
    public int updateApplyOrder(List<MachineOrder> orders) {
        return machineMapper.updateApplyOrder(orders);
    }

    @Override
    public int updateReturnOrder(List<MachineOrder> orders) {
        return machineMapper.updateReturnOrder(orders);
    }
}
