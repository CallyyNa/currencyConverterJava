package com.fdmgroup.CurrencyConverter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.CurrencyConverter.Controller.ConverterController;
import com.fdmgroup.CurrencyConverter.config.AppConfig;

public class SpringApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ConverterController controller = context.getBean(ConverterController.class);
		
		controller.startup();
		controller.convert();
		
		context.close();
	}

}
