package com.imooc.SpringBootFreeMarker.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan("com.imooc.SpringBootFreeMarker")
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
   @Bean
	public FreeMarkerViewResolver getFreeMarkerViewSovler(){
		FreeMarkerViewResolver freeMarkerViewSovler = new FreeMarkerViewResolver();
		freeMarkerViewSovler.setCache(false);
		freeMarkerViewSovler.setContentType("text/html;charset=utf-8");
		freeMarkerViewSovler.setRequestContextAttribute("request");
		freeMarkerViewSovler.setOrder(1);
		freeMarkerViewSovler.setSuffix(".html");
		freeMarkerViewSovler.setViewClass(FreeMarkerView.class);
		return freeMarkerViewSovler;
	}
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
   }
   @Bean
   public FormHttpMessageConverter formHttpMessageConverter(){
	   FormHttpMessageConverter fastConverter = new FormHttpMessageConverter();
	  fastConverter.setCharset(Charset.forName("UTF-8"));
	   List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
	   supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
	   fastConverter.setSupportedMediaTypes(supportedMediaTypes);
	   return fastConverter;
   }
   @Bean
   public FreeMarkerConfigurer getFreeMarkerConfigurer(){
	   FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
	   freeMarkerConfigurer.setDefaultEncoding("UTF-8");
	   freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
	   Properties prop = new Properties();
	   prop.setProperty("template_update_delay", "5");
       prop.setProperty("url_escaping_charset", "UTF-8");
       prop.setProperty("defaultEncoding", "UTF-8");
       prop.setProperty("whitespace_stripping", "true");
       prop.setProperty("boolean_format", "true,false");
       prop.setProperty("number_format", "0.##########");
       prop.setProperty("locale", "zh_CN");
       prop.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
       prop.setProperty("date_format", "yyyy-MM-dd");
       prop.setProperty("time_format", "HH:mm:ss");
       prop.setProperty("tag_syntax", "square_bracket");
       prop.setProperty("classic_compatible", "true");
       prop.setProperty("template_exception_handler", "ignore");
       prop.setProperty("auto_import", "/spring.ftl as spring, /common/spring.ftl as spring");
       freeMarkerConfigurer.setFreemarkerSettings(prop);
       Map<String,Object> variables = new HashMap<String,Object>();
       variables.put("role", new RoleDirectiveModel());
       freeMarkerConfigurer.setFreemarkerVariables(variables);
	   return freeMarkerConfigurer;
   }
   @Override
   public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       super.configureMessageConverters(converters);
       converters.add(this.formHttpMessageConverter());
   }
}
