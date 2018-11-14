package com.hydee.ydky.service.impl;

import com.hydee.ydky.dao.StockMapper;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.Stock;
import com.hydee.ydky.service.APIStockService;
import javafx.beans.binding.When;
import jdk.nashorn.internal.objects.annotations.Where;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

@Service
public class APIStockServiceImpl implements APIStockService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.datasource.commitCount}")
    private int commitCount;

    @Autowired
    StockMapper stockMapper;

    @Async
    @Override
    public void stockUpdate() {
        logger.info("数据拉取开始...");
        List<Stock> list = stockMapper.selectStock();
        int size = list.size();
        int pageCount = size % commitCount == 0 ? size / commitCount : size / commitCount + 1;

        logger.info("数据拉取中，size="+size+" pageCount:" + pageCount);
        for (int i = 0; i < pageCount; i++) {
            int pageSize;
            if(i >= pageCount - 1){
                pageSize = size % commitCount;
            }else {
                pageSize = commitCount;
            }
            List<Stock> stocks = new ArrayList<>(list.subList(0, pageSize));
            list.subList(0, pageSize).clear();
            logger.info("数据拉取中，pageNum:"+i+" stocksSzie:"+stocks.size()+" 剩余:"+list.size());

        }

    }

    @Override
    public int specialOutage(MachineSellOrder order) {
        return stockMapper.specialOutage(order);
    }
}
