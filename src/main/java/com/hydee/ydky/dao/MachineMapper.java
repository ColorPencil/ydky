package com.hydee.ydky.dao;

import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.OrderLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface MachineMapper {

    OrderLog selectLastTime(@Param("busno") String busno, @Param("type") int type);

    int insertLastTime(OrderLog orderLog);

    int updateLastTime(OrderLog orderLog);

    int insertApplyOrder(MachineOrder order);

    int insertReturnOrder(MachineOrder order);

    int insertSellOrder(MachineSellOrder order);

    List<MachineOrder> selectApplyOrderByTime(@Param("busno") String busno, @Param("timestamp") Date timestamp);//, @Param("type") String type

    List<MachineOrder> selectReturnOrderByTime(@Param("busno") String busno, @Param("timestamp") Date timestamp);

    List<MachineSellOrder> selectSellOrderByTime(@Param("busno") String busno, @Param("timestamp") Date timestamp);

    int updateApplyOrder(List<MachineOrder> orders);

    int updateReturnOrder(List<MachineOrder> orders);
}
