package com.hydee.ydky.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hydee.ydky.dao.EnterMapper;
import com.hydee.ydky.dao.MachineMapper;
import com.hydee.ydky.entity.EnterGood;
import com.hydee.ydky.entity.EnterMachine;
import com.hydee.ydky.entity.EnterStore;
import com.hydee.ydky.service.APIEnterService;
import com.hydee.ydky.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niang on 2018/11/12.
 */
@Service
public class APIEnterServiceImpl implements APIEnterService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EnterMapper enterMapper;

    @Value("${spring.datasource.newStoreUrl}")
    private String storeUrl;

    @Value("${spring.datasource.newMachineUrl}")
    private String machineUrl;


    @Override
    public String newStore(EnterStore store){
        //return enterMapper.insertStore(store);
        Map<String, String> header = new HashMap<>();
        header.put("Content-type", "application/json;charset=UTF-8");
        try {
            String json= JSONObject.toJSONString(store);
            String response = HttpUtils.post(storeUrl,json,header);
            logger.info(response);
            return response;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public String newMachine(EnterMachine machine){
        //return enterMapper.insertMachine(machine);
        Map<String, String> header = new HashMap<>();
        header.put("Content-type", "application/json;charset=UTF-8");
        try {
            String res = HttpUtils.post(machineUrl,JSONObject.toJSONString(machine),header);
            logger.info(res);
            return res;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    };

    @Override
    public List<EnterGood> getGoodsInfo(int pageIndex , int pageSize){

        PageHelper.startPage(pageIndex,pageSize);
        return enterMapper.selectGoods();
    }



}
