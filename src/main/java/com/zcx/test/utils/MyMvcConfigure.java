package com.zcx.test.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springboot的拦截器写法 1：自定义类 实现WebMvcConfigurer 2：该接口中有一个api 叫做 添加拦截
 *
 */
@Configuration
public class MyMvcConfigure implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	// 在此方法中添加 自定义拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		// *代表拦截api下的1级目录 ** 代表拦截一级目录下的所有目录
		registry.addInterceptor(loginInterceptor).addPathPatterns("/consumer/**") // *代表拦截consumer下的1级目录 ** 代表拦截目录下的所有目录
				.excludePathPatterns("/consumer/user/login", "/consumer/user/add", "/test/**");// 不拦截的路径
	}
}

