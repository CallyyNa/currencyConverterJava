package com.fdmgroup.CurrencyConverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.CurrencyConverter.model.Converter;
import com.fdmgroup.CurrencyConverter.model.ConvertingLogic;
import com.fdmgroup.CurrencyConverter.model.CurrencyData;
import com.fdmgroup.CurrencyConverter.model.CurrencyOptionData;
import com.fdmgroup.CurrencyConverter.model.JSONCurrencyOptionData;
import com.fdmgroup.CurrencyConverter.model.URLCurrencyData;

@Configuration
@ComponentScan(basePackages= {"com.fdmgroup.CurrencyConverter"})
public class AppConfig {

	@Bean
	public Converter createConverter() {
		return new ConvertingLogic(createCurrencyData());
	}
	
	@Bean 
	public CurrencyData createCurrencyData(){
//		return new XMLCurrencyData();
		return new URLCurrencyData();
	}
	
	@Bean 
	public CurrencyOptionData createCurrencyOptions() {
		return new JSONCurrencyOptionData();
	}
}
