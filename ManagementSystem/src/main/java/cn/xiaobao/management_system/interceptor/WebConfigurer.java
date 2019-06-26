package cn.xiaobao.management_system.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer{
	@Autowired
	LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = 
				registry.addInterceptor(loginInterceptor);
		addInterceptor.addPathPatterns("/**").excludePathPatterns("/index.html")
		.excludePathPatterns("/CameraRoll/**").excludePathPatterns("/jquery-1.4.2.min.js")
		.excludePathPatterns("/user/handle_login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		
	}
	
	
}
