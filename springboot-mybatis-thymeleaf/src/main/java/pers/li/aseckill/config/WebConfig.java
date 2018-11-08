package pers.li.aseckill.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义配置拦截器： extends WebMvcConfigurerAdapter
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{
	
	@Autowired
	UserArgumentResolver userArgumentResolver;

	/**
	 * controller中接收参数，赋值过程再此处完成 -->参数解析
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	/*返回值解析*//*
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
	}*/

	/*异常处理解析器*//*
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
	}*/
	
	
}
