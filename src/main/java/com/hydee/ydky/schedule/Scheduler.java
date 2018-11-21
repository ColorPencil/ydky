package com.hydee.ydky.schedule;

import com.hydee.ydky.service.APIStockService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    APIStockService apiStockService;

    /*@Scheduled(cron = "0 0/5 * * * ?")
    public void updateStock(){
        logger.info("库存更改抓取...");
        apiStockService.stockUpdate();
    }*/
}
