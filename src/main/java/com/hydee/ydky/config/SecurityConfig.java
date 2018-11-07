package com.hydee.ydky.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 处理用户登录及权限验证
 * 
 * ====================================
 * @author 	:LuoF
 * @date 	:2018年3月20日
 * @version :1.0
 * @remark	:
 * ====================================
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
			.antMatchers("/api/**","/drugcode/**","/invalidSession","/sendPhoneVerCode","/captcha/**").permitAll()
	        .anyRequest()
	        .authenticated()
	        .and()
        	.formLogin()                    	//启用默认登陆页面
        	.loginPage("/login")
        	.usernameParameter("username")		//采用用户编码或手机号码登录
        	.passwordParameter("userpass")		//修正密码框名称
            .failureUrl("/login?error")			//登陆失败返回URL:/login?error
            .defaultSuccessUrl("/index")		//登陆成功跳转URL
            .permitAll()                    	//登陆页面全部权限可访问
	        .and()
            .logout()							//注销
            .permitAll()
            .and()
            .sessionManagement()
            .maximumSessions(1)					//最大session并发数
            .maxSessionsPreventsLogin(false);	//是否保持当前用户登录
        // 关闭csrf 防止循环定向
        http.csrf().disable();
        // session失效后跳转
        http.sessionManagement().invalidSessionUrl("/login");
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		/* 首页 */
		web.ignoring().antMatchers("/index","/");
		/* 错误页面 */
		web.ignoring().antMatchers("/error/**");
		/* 上传资源路径 */
        web.ignoring().antMatchers("/upload/**");
        web.ignoring().antMatchers("/image/**");
        web.ignoring().antMatchers("/file/**");
        web.ignoring().antMatchers("/media/**");
        /* 条形码及二维码资源 */
        web.ignoring().antMatchers("/getCode");
        web.ignoring().antMatchers("/qrCode/**");
        web.ignoring().antMatchers("/barCode/**");
        /* 静态资源路径 */
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/font-awesome/**");
        web.ignoring().antMatchers("/js/**");
        /* 网页图标 */
        web.ignoring().antMatchers("/favicon.ico");
        /* druid监控 */
        web.ignoring().antMatchers("/druid/**");
    }
	
}
