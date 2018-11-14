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
    public OrderLog selectLastTime(String busno, int type) {
        return machineMapper.selectLastTime(busno, type);
    }

    @Override
    public int insertLastTime(String busno, Date timestamp, int type) {
        int commit;
        OrderLog orderLog = machineMapper.selectLastTime(busno, type);
        if(orderLog == null){
            orderLog = new OrderLog(timestamp, timestamp, busno, type);
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
    public List<MachineOrder> selectApplyOrderByTime(String busno, Date timestamp) {
        return machineMapper.selectApplyOrderByTime(busno, timestamp);
    }

    @Override
    public List<MachineOrder> selectReturnOrderByTime(String busno, Date timestamp) {
        return machineMapper.selectReturnOrderByTime(busno, timestamp);
    }

    @Override
    public List<MachineSellOrder> selectSellOrderByTime(String busno, Date timestamp) {
        return machineMapper.selectSellOrderByTime(busno, timestamp);
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
