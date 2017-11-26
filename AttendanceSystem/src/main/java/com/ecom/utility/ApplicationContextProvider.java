package com.ecom.utility;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT = applicationContext;
	}

	/**
	 * Get a Spring bean by type.
	 **/
	public static <T> T getBean(String beanName, Class<T> beanClass) throws NoSuchBeanDefinitionException{
		return CONTEXT.getBean(beanName, beanClass);
	}
}