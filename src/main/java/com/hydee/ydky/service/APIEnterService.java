package com.hydee.ydky.service;

import com.github.pagehelper.PageInfo;
import com.hydee.ydky.entity.EnterGood;
import com.hydee.ydky.entity.EnterMachine;
import com.hydee.ydky.entity.EnterStore;

import java.util.List;

/**
 * Created by niang on 2018/11/12.
 */
public interface APIEnterService {

    String newStore(EnterStore store);

    String newMachine(EnterMachine machine);

    List<EnterGood> getGoodsInfo(int size , int index);
}
