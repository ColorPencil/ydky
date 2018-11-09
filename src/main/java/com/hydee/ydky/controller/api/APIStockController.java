package com.hydee.ydky.controller.api;

import com.hydee.ydky.constants.ResponseObject;
import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.MachineSellOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class APIStockController {

    @PostMapping("/specialOutage")
    public Object specialOutage(@RequestBody(required = false) MachineSellOrder order){
        return ResponseObject.Failed();
    }
}
