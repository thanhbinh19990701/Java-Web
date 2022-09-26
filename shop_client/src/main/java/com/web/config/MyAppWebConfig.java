package com.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyAppWebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		Custom các đường dẩn cho giao diện để nhận thư viện css, js, image

		ResourceHandlerRegistration resourceRegistration = registry.addResourceHandler("resources/**");
		resourceRegistration.addResourceLocations("/resources/**");
		registry.addResourceHandler("/templates/css/**").addResourceLocations("/templates/css/**");
		registry.addResourceHandler("/templates/images/**").addResourceLocations("/templates/images/**");
		registry.addResourceHandler("/templates/js/**").addResourceLocations("/templates/js/**");
		registry.addResourceHandler("/templates/**").addResourceLocations("/templates/**");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		// do the classpath works with the directory under webapp?
	}

}
