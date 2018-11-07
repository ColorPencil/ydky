package com.hydee.ydky.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.hydee.ydky.constants.ResponseObject;
import com.hydee.ydky.constants.Results;
import com.hydee.ydky.utils.DateUtils;

/**
 * API接口拦截器
 *  - 验证用户身份合法性
 *  - 验证用户访问权限
 * ====================================
 * @author 	:LuoF
 * @date 	:2018年7月10日
 * @version :1.0
 * @remark	:
 * ====================================
 */
public class APIInterceptor implements HandlerInterceptor {
	@Value("${server.api.sign.expired}")
	private long enablesec = 300l;
	
	@Value("${server.api.sign.enabled}")
	private boolean signEnable = true;
	
	/**
	 * 对API接口进行校验,拦截无效请求
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if(!signEnable) return true;
    	String timestamp = request.getParameter("timestamp");
//    	String sign = request.getParameter("sign");
    	Results result = Results.SIGN_NOT_AVAILABLE;
    	if(DateUtils.compareSeconds(timestamp, "yyyyMMddHHmmss") > enablesec) {
    		result = Results.SIGN_IS_TIMEOUT;
    	} else {
    		return true;
    	}
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter printWriter = response.getWriter();
		try{
			printWriter.write(JSONObject.toJSONString(ResponseObject.Create(result))); 
			printWriter.flush();
			return false;
		} finally {
			printWriter.close();
		}
    }
    
}
