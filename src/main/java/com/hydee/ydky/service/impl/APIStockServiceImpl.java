package com.hydee.ydky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hydee.ydky.dao.StockMapper;
import com.hydee.ydky.dao.impl.BaseMapper;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.Stock;
import com.hydee.ydky.entity.StoreLog;
import com.hydee.ydky.service.APIStockService;
import com.hydee.ydky.utils.HttpUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class APIStockServiceImpl implements APIStockService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.datasource.commitCount}")
    private int commitCount;

    @Value("${spring.datasource.stockUpdateUrl}")
    private String stockUpdateUrl;

    @Autowired
    StockMapper stockMapper;
    @Autowired
    BaseMapper baseMapper;

    @Async
    @Override
    public void stockUpdate() {
        logger.info("数据拉取开始...");
        //执行存储过程生成库存数据
        int num = stockMapper.autoGenerateStore();
        //查询库存
        List<Stock> list = stockMapper.selectStock();
        int size = list.size();
        //根据条数来分页
        int pageCount = size % commitCount == 0 ? size / commitCount : size / commitCount + 1;

        logger.info("数据拉取完成，size="+size+" pageCount:" + pageCount);
        //循环插入
        for (int i = 0; i < pageCount; i++) {
            int pageSize;
            if(i >= pageCount - 1){
                pageSize = size % commitCount;
            }else {
                pageSize = commitCount;
            }
            List<Stock> stocks = new ArrayList<>(list.subList(0, pageSize));
            list.subList(0, pageSize).clear();
            logger.info("数据推送，pageNum:"+i+" stocksSzie:"+stocks.size()+" 剩余:"+list.size());
            Map<String, String> head = new HashMap<>();
            head.put("Content-Type", "application/json;charset=utf-8");
            logger.info("数据推送中...");
            //向对方接口推送数据
            String result = HttpUtils.post(stockUpdateUrl, JSON.toJSONString(stocks), head);
            JSONObject jsonObject = JSONObject.parseObject(result);
            //获取返回的失败条数
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if(jsonArray.size() > 0){
                logger.info("插入错误日志开始， 条数："+jsonArray.size());
                List<StoreLog> storeLogs = new ArrayList<>();
                Date date = new Date();
                //循环插入错误日志
                for (int j = 0; j < jsonArray.size(); j++) {
                    JSONObject cdata = jsonArray.getJSONObject(j);
                    String wareid = cdata.getString("wareid");
                    String busno = cdata.getString("busNo");
                    String mes = cdata.getString("mes");
                    storeLogs.add(new StoreLog(busno, wareid, date, mes));
                }
                baseMapper.batchInsert("com.hydee.ydky.dao.StockMapper", "inserStoreLog", storeLogs);
//                stockMapper.inserStoreLog(storeLogs);
                logger.info("插入错误日志结束...");
            }
            logger.info("数据推送结束...");
        }

    }

    @Override
    public int specialOutage(MachineSellOrder order) {
        return stockMapper.specialOutage(order);
    }
}
