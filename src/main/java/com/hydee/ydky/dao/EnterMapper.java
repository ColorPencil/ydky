package com.hydee.ydky.dao;

import com.hydee.ydky.entity.EnterGood;
import com.hydee.ydky.entity.EnterMachine;
import com.hydee.ydky.entity.EnterStore;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by niang on 2018/11/12.
 */
@Component
public interface EnterMapper {

    int insertStore(EnterStore store);

    int insertMachine(EnterMachine machine);

    List<EnterGood> selectGoods();
}
