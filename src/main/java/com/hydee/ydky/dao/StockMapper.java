package com.hydee.ydky.dao;

import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StockMapper {

        public List<Stock> selectStock();

        public int specialOutage(MachineSellOrder order);
}
