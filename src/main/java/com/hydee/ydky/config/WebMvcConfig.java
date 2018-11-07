package com.hydee.ydky.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hydee.ydky.interceptor.APIInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大
		factory.setMaxFileSize("10240KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
	
	/**
	 * 追加拦截器
	 */
	@Bean
	public APIInterceptor apiInterceptor() {
		return new APIInterceptor();
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor( apiInterceptor() )   	// 追加API接口签名验证拦截器
        		.excludePathPatterns("/api","/api/testDrugcode")			// 接口首页文档地址不拦截
                .addPathPatterns("/api/**");			// 拦截所有接口访问
    }
	
}
