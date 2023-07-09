package com.spring.config;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.spring.interceptor.AdminInterceptor;
import com.spring.interceptor.LoginInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	@Bean("messageSource")
	public MessageSource loadMessageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		
		source.setBasenames(
							"classpath:message/valiate",
							"classpath:i18n/home"
				);
		
		source.setDefaultEncoding(StandardCharsets.UTF_8.name());
		return source;
	}
	
	@Bean("localeResolver")
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setCookieMaxAge(60);
		cookieLocaleResolver.setCookiePath("/");
		cookieLocaleResolver.setDefaultLocale(new Locale("en"));
		return cookieLocaleResolver;
	}
	
	@Autowired
	private LoginInterceptor loginIn;
	
	@Autowired
	private AdminInterceptor adminIn;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginIn)
		.addPathPatterns("/shopping-cart/**", "/checkout/**","/settingAccount/**")
		.excludePathPatterns("/login", "/register","/index2/logout");
		
		registry.addInterceptor(adminIn)
		.addPathPatterns("/admin/home/**")
		.excludePathPatterns("/login", "/register","/index2/logout");
		
		LocaleChangeInterceptor changeInterceptor  = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("language");
		registry.addInterceptor(changeInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/admin/**");
	}
}
