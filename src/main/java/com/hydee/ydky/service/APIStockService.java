package com.hydee.ydky.service;

import com.hydee.ydky.entity.MachineSellOrder;

public interface APIStockService {

    public void stockUpdate();

    public int specialOutage(MachineSellOrder order);
}
