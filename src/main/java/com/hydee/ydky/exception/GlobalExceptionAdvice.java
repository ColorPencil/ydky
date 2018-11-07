package com.hydee.ydky.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONException;
import com.hydee.ydky.constants.ResponseObject;
import com.hydee.ydky.constants.Results;
import com.hydee.ydky.utils.StringUtils;

/**
 * 处理全局异常捕获
 * ==========================================================
 * @author LuoF
 * @date 2018-03-07
 * @version 1.0
 * @remark 当前属于临时版本,待后续完善
 * ==========================================================
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object handleBadRequestException(MissingServletRequestParameterException ex) {
		return ResponseObject.Create(Results.MISSING_REQUEST_PARAMS, ex.getMessage());
	}
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object handleBadRequestException(HttpMessageNotReadableException ex) {
		return ResponseObject.Create(Results.RESOLVER_PARAMS_FAILED, ex.getMessage());
	}
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value={MethodArgumentNotValidException.class, ConstraintViolationException.class, ValidationException.class})
	public Object handleBadRequestException(MethodArgumentNotValidException ex) {
		return ResponseObject.Create(Results.CHECKED_PARAMS_FAILED, ex.getMessage());
	}
	
	/**
	 * 404 - Not Found
	 * 暂时无法完美捕获
	 */
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = {NoHandlerFoundException.class})
//    public Object noMapping(HttpServletRequest request, HttpServletResponse response, Object object, NoHandlerFoundException exception) {
//		String method = request.getMethod();
//		String contentType = request.getHeader("Content-Type");
//		String accept = request.getHeader("accept");
//		String xRequestWith = request.getHeader("X-Requested-With");
//		// 判断是否为AJAX或者POST和JSON请求
//		if( ( method != null && method.toUpperCase().trim().equals("POST") ) ||
//			( contentType != null && contentType.toLowerCase().contains("application/json") ) ||
//			( accept != null && accept.contains("application/json") ) ||
//			( xRequestWith != null && xRequestWith.contains("XMLHttpRequest") ) ) {
//			return ResponseObject.Create(Results.NOT_FOUND_REQUEST, exception.getMessage());
//		} else {
//            //对于非AJAX请求，我们都统一跳转到error页面
//        	ModelAndView m = new ModelAndView();
//            m.addObject("ex", exception.getMessage());
//            m.addObject("exMsg", StringUtils.getStackTrace(exception));
//            m.setViewName("error/404");
//            return m;
//        }
//    }
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Object handleBadRequestException(BindException ex) {
		return ResponseObject.Create(Results.BIND_PARAMS_FAILED, ex.getMessage());
	}
	
	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Object handleBadRequestException(HttpRequestMethodNotSupportedException ex) {
		return ResponseObject.Create(Results.METHOD_NOT_ALLOWED, ex.getMessage());
	}
	
	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Object handleBadRequestException(HttpMediaTypeNotSupportedException ex) {
		return ResponseObject.Create(Results.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
	}
	
	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({RuntimeException.class, Exception.class})
	public Object processException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
		String method = request.getMethod();
		String contentType = request.getHeader("Content-Type");
		String accept = request.getHeader("accept");
		String xRequestWith = request.getHeader("X-Requested-With");
		// 判断是否为AJAX或者POST和JSON请求
		if( ( method != null && method.toUpperCase().trim().equals("POST") ) ||
			( contentType != null && contentType.toLowerCase().contains("application/json") ) ||
			( accept != null && accept.contains("application/json") ) ||
			( xRequestWith != null && xRequestWith.contains("XMLHttpRequest") ) ) {
			if(exception instanceof AccessDeniedException) {
				return ResponseObject.Create(Results.NO_AUTHS);
			} else if(exception instanceof JSONException) {
				return ResponseObject.Create(Results.RESOLVER_PARAMS_FAILED, exception.getMessage());
			} else if(exception instanceof RedisConnectionFailureException) {
				return ResponseObject.Create(Results.REDIS_SERVER_IS_UNSTARTED);
			} else {
				logger.error(exception.getMessage(), exception);
				return ResponseObject.Failed(exception);
			}
		} else {
            //对于非AJAX请求，我们都统一跳转到error页面
        	ModelAndView m = new ModelAndView();
        	if(exception instanceof AccessDeniedException) {
        		m.addObject("title", "抱歉,您没有权限访问此页面!");
        	} else if(exception instanceof RedisConnectionFailureException) {
        		m.addObject("title", Results.REDIS_SERVER_IS_UNSTARTED.getMsg());
        	} else {
        		logger.error("", exception);
        	}
            m.addObject("ex", exception.getMessage());
            m.addObject("exMsg", StringUtils.getStackTrace(exception));
            m.setViewName("error/500");
            return m;
        }
	}
	
}
