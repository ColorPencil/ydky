package com.hydee.ydky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydee.ydky.dao.TestMapper;
import com.hydee.ydky.service.APIService;

@Service
public class APIServiceImpl implements APIService {
	@Autowired
	TestMapper testMapper;
	
	@Override
	public String test() {
		return testMapper.selectTest();
	}

}
