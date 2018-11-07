package com.hydee.ydky.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 使用 FastJson 解析JSON试图(目前JSON解析最快的开源组件)
 * ====================================
 * @author 	:LuoF
 * @date 	:2018年3月7日
 * @version :1.0
 * @remark	:
 * ====================================
 */
@Configuration
public class FastJsonConfiguration implements WebMvcConfigurer {
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		WebMvcConfigurer.super.configureMessageConverters(converters);
		//创建fastjson消息转换器
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		//创建配置类
		FastJsonConfig config = new FastJsonConfig();
		//修改配置返回内容的过滤
		config.setSerializerFeatures(
			/*
		    SerializerFeature.PrettyFormat:格式化输出
		    SerializerFeature.WriteMapNullValue:是否输出值为null的字段,默认为false
		    SerializerFeature.DisableCircularReferenceDetect:消除循环引用
		    WriteNullListAsEmpty：List字段如果为null,输出为[],而非null
		    WriteNullNumberAsZero：数值字段如果为null,输出为0,而非null
		    WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
		    SkipTransientField：如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
		    SortField：按字段名称排序后输出。默认为false
		    WriteDateUseDateFormat：全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
		    BeanToArray：将对象转为array输出
		    QuoteFieldNames：输出key时是否使用双引号,默认为true
		    UseSingleQuotes：输出key时使用单引号而不是双引号,默认为false（经测试，这里的key是指所有的输出结果，而非key/value的key,而是key,和value都使用单引号或双引号输出）
			*/
			SerializerFeature.WriteNullStringAsEmpty,//将为null的字段值显示为""
			SerializerFeature.DisableCircularReferenceDetect,
			SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteNullBooleanAsFalse,
			SerializerFeature.WriteNullListAsEmpty,
			SerializerFeature.WriteNullStringAsEmpty
		);
		//处理中文乱码问题
		List<MediaType> fastMediaType = new ArrayList<>();
		fastMediaType.add(MediaType.APPLICATION_JSON_UTF8);
		converter.setSupportedMediaTypes(fastMediaType);
		converter.setFastJsonConfig(config);
		
		//将fastjson添加到视图消息转换器列表内
		converters.add(converter);
	}
	
}
