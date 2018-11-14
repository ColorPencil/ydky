package com.hydee.ydky.controller.api;

import com.hydee.ydky.constants.ResponseObject;
import com.hydee.ydky.constants.Results;
import com.hydee.ydky.entity.MachineSellOrder;
import com.hydee.ydky.entity.MachineOrder;
import com.hydee.ydky.entity.OrderLog;
import com.hydee.ydky.service.APIMachineService;
import com.hydee.ydky.utils.Enums;
import com.sun.xml.internal.bind.v2.TODO;
import org.aspectj.weaver.Lint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 补货申请 & 退货申请
 */
@RestController
@RequestMapping("/api/machine")
public class APIMachineController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    APIMachineService apiMachineService;

    /**
     * 易点快药调用
     * 补货申请（插入单据）
     * @param order
     * @return
     */
    @PostMapping("/apply")
    public Object apply(@RequestBody(required = false) MachineOrder order){
        if(order == null || order.getDetail() == null || order.getDetail().isEmpty()) return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        Date date = new Date(System.currentTimeMillis());
        order.setCreateTime(date);
        order.setLastTime(date);
        int commit = apiMachineService.apply(order);
        if(commit > 0){
            return ResponseObject.Success();
        }else{
            return ResponseObject.Failed();
        }
    }

    /**
     * 易点快药调用
     * 退货申请（插入单据）
     * @param order
     * @return
     */
    @PostMapping("/return")
    public Object dcmReturn(@RequestBody(required = false) MachineOrder order){
        if(order == null || order.getDetail() == null || order.getDetail().isEmpty()) return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        Date date = new Date(System.currentTimeMillis());
        order.setCreateTime(date);
        order.setLastTime(date);
        int commit = apiMachineService.dcmReturn(order);
        if(commit > 0){
            return ResponseObject.Success();
        }else{
            return ResponseObject.Failed();
        }
    }

    /**
     * 易点快药调用
     * 零售申请（插入单据）
     * @param order
     * @return
     */
    @PostMapping("/sell")
    public Object sell(@RequestBody(required = false) MachineSellOrder order){
        if(order == null || order.getDetail() == null || order.getDetail().isEmpty()) return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        Date date = new Date(System.currentTimeMillis());
        order.setCreateTime(date);
        order.setLastTime(date);
        int commit = apiMachineService.sell(order);
        if(commit > 0){
            return ResponseObject.Success();
        }else{
            return ResponseObject.Failed();
        }
    }

    /**
     * 小程序调用
     * 查询指定更新时间之后的补货单据
     * @param type
     * @return
     */
    @RequestMapping("/selectNewOrder")
    public Object selectOrderByTime(String busno, int type){
        if(busno == null || busno.isEmpty()) {
            return ResponseObject.Create(Results.COM_REQUEST_PARAMS_NOT_FULL).setMsg(String.format(Results.COM_REQUEST_PARAMS_NOT_FULL.getMsg(), "busno"));
        }
        try {
            OrderLog orderLog = apiMachineService.selectLastTime(busno, type);
            Date timestamp = null;
            if(orderLog != null){
                timestamp = orderLog.getuTime();
            }
            List list;
            if(type == Enums.ORDER_LOG_TYPE_APPLY.getCode()){
                list = apiMachineService.selectApplyOrderByTime(busno, timestamp);
            }else if(type == Enums.ORDER_LOG_TYPE_RETURN.getCode()){
                list = apiMachineService.selectReturnOrderByTime(busno, timestamp);
            }else if(type == Enums.ORDER_LOG_TYPE_SELL.getCode()){
                list = apiMachineService.selectSellOrderByTime(busno, timestamp);
            }else{
                return ResponseObject.Create(Results.MACHINE_DCM_TYPE_NOT_fOUND);
            }
            if(list.isEmpty()){
                return ResponseObject.Create(Results.MACHINE_DCM_TYPE_NOT_UPDATEED);
            }else{
                return ResponseObject.Success().setData(list);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseObject.Failed();
        }
    }

    /**
     * 小程序端调用
     * 回调查询数据是否成功
     * @param timestamp
     * @return
     */
    @PostMapping("/selectOrderByTimeCallback")
    public Object selectOrderByTimeCallback(String busno, Long timestamp, int type){
        if(timestamp == null) return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        int commit = apiMachineService.insertLastTime(busno, new Date(timestamp), type);
        if(commit > 0){
            return ResponseObject.Success();
        }else{
            return  ResponseObject.Failed();
        }
    }

    /**
     * 小程序调用
     * 更新补货单据并调用易点快药接口
     * @param orders
     * @return
     */
    @PostMapping("/updateApplyOrder")
    public Object updateApplyOrder(@RequestBody(required = false) List<MachineOrder> orders){
        if(orders == null || orders.isEmpty()) return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setLastTime(new Date(System.currentTimeMillis()));
        }
        int commit = apiMachineService.updateApplyOrder(orders);
        if(commit > 0){
            return ResponseObject.Success();
        }else{
            return ResponseObject.Failed();
        }
    }

    /**
     * 小程序调用
     * 更新退货单据并调用易点快药接口
     * @param orders
     * @return
     */
    @PostMapping("/updateReturnOrder")
    public Object updateReturnOrder(@RequestBody(required = false) List<MachineOrder> orders){
        if(orders == null || orders.isEmpty()) return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setLastTime(new Date(System.currentTimeMillis()));
        }
        int commit = apiMachineService.updateReturnOrder(orders);
        if(commit > 0){
            return ResponseObject.Success();
        }else{
            return ResponseObject.Failed();
        }
        //TODO 调用易点快药接口 待对接
    }
}
