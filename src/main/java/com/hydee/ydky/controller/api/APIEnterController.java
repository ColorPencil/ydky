package com.hydee.ydky.controller.api;


import com.hydee.ydky.constants.ResponseObject;
import com.hydee.ydky.constants.Results;
import com.hydee.ydky.entity.EnterGood;
import com.hydee.ydky.entity.EnterMachine;
import com.hydee.ydky.entity.EnterStore;
import com.hydee.ydky.service.APIEnterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by niang on 2018/11/12.
 */
@Controller
@RequestMapping("/api/enter")
public class APIEnterController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    APIEnterService apiEnterService;

    /**
     * 海典调用
     * 新增门店（插入单据）
     * @param store
     * @return
     */
    @PostMapping("/newStore")
    @ResponseBody
    public Object store(@RequestBody(required = false) EnterStore store){
        if(store == null)
            return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        String res  = apiEnterService.newStore(store);
        if(res==null){
            return ResponseObject.Failed();
        }else{
            return res;
        }
    }


    /**
     * 海典调用
     * 新增售药机（插入单据）
     * @param machine
     * @return
     */
    @PostMapping("/newMachine")
    @ResponseBody
    public Object machine(@RequestBody(required = false) EnterMachine machine){
        if(machine == null)
            return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS);
        String res = apiEnterService.newMachine(machine);
        if(res==null){
            return ResponseObject.Failed();
        }else{
            return res;
        }
    }


    /**
     * 易点调用
     * 分页获取商品信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/goodsInfo")
    @ResponseBody
    public Object goods(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex ,@RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize){

        List<EnterGood> goods = apiEnterService.getGoodsInfo(pageIndex,pageSize);
        if(goods !=null && !goods.isEmpty()){
            ResponseObject res  = ResponseObject.Success();
            res.setData(goods);
            return res;
        }else{
            return ResponseObject.Failed();
        }
    }

}
