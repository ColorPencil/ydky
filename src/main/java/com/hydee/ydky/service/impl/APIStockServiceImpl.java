package com.hydee.ydky.service.impl;

import com.hydee.ydky.dao.StockMapper;
import com.hydee.ydky.entity.Stock;
import com.hydee.ydky.service.APIStockService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIStockServiceImpl implements APIStockService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StockMapper stockMapper;

    @Async("asyncServiceExecutor")
    @Override
    public void stockUpdate() {
        logger.info("数据拉取开始...");
        List<Stock> list = stockMapper.selectStock();
        logger.info("数据拉取结束，size="+list.size());
    }
}
