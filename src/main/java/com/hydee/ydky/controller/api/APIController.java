package com.hydee.ydky.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.ydky.constants.ResponseObject;
import com.hydee.ydky.constants.Results;
import com.hydee.ydky.service.APIService;

/**
 * 本控制层用于处理用户验证等基础信息接口逻辑
 * 
 * ====================================
 * @author 	:LuoF
 * @date 	:2018年4月20日
 * @version :1.0
 * @remark	:
 * ====================================
 */
@Controller
@RequestMapping("/api")
public class APIController {
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	APIService apiService;
	
	/**
	 * 跳转至线上接口文档(访问密码:hydeesoft)
	 * @return
	 */
	@GetMapping("")
	public String index() {
		return "redirect:https://www.showdoc.cc/web/#/55575694260298?page_id=313941105476638";
	}
	
	/**
	 * post请求接口首页返回信息
	 * @return
	 */
	@PostMapping("")
	@ResponseBody
	public Object postIndex() {
		ResponseObject<String> responseObject = new ResponseObject<String>();
		responseObject.setCode(Results.COM_SUCCESS.getCode());
		responseObject.setMsg("接口文档地址");
		responseObject.setData("https://www.showdoc.cc/web/#/55575694260298?page_id=313941105476638");
		return responseObject;
	}
	
	@PostMapping("/test")
	@ResponseBody
	public Object test() {
		logger.info("test api interface...");
		return ResponseObject.Success().setData(apiService.test());
	}
}
