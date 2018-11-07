package com.hydee.ydky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	/** 静态页面映射配置 */
	private static final String VIEWS_INDEX = "index";
	
	/**
	 * 首页
	 * @return
	 */
	@GetMapping("/index")
	public String index (ModelMap modelMap) throws Exception {
		return VIEWS_INDEX;
	}
}
