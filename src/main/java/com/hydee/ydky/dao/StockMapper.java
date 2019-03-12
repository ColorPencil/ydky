package com.hydee.ydky.dao;

import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.Stock;
import com.hydee.ydky.entity.StoreLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StockMapper {

        public int autoGenerateStore();

        public List<Stock> selectStock();

        public int inserStoreLog(List<StoreLog> list);

        public int specialOutage(MachineSellOrder order);
}
