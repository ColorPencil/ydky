package com.hydee.ydky.controller.api;

import com.hydee.ydky.constants.ResponseObject;
import com.hydee.ydky.constants.Results;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.service.APIStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/stock")
public class APIStockController {

    @Autowired
    APIStockService apiStockService;

    @PostMapping("/specialOutage")
    public Object specialOutage(@RequestBody(required = false) MachineSellOrder order){
        if(order == null || order.getDetail() == null || order.getDetail().isEmpty()) return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        Date date = new Date(System.currentTimeMillis());
        order.setCreateTime(date);
        order.setLastTime(date);
        int commit = apiStockService.specialOutage(order);
        if(commit > 0){
            return ResponseObject.Success();
        }else{
            return ResponseObject.Failed();
        }
    }

    /*@RequestMapping("/test")
    public String test(){
        apiStockService.stockUpdate();
        return "test";
    }*/
}
