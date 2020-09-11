package com.fdmgroup.CurrencyConverter.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertingLogic implements Converter{
	private final CurrencyData currencyData;
	

	public ConvertingLogic(CurrencyData theCurrencyData) {
		this.currencyData = theCurrencyData;
	}
	
	public BigDecimal convertToEUR(String currency, BigDecimal value) {
		BigDecimal exchangeRate = currencyData.getExchangeRate(currency);
		BigDecimal totalAfterConversion = value.divide(exchangeRate, 2, RoundingMode.CEILING);
		
		return totalAfterConversion;
	}

	public BigDecimal convertFromEUR(String currency, BigDecimal value) {
		BigDecimal exchangeRate = currencyData.getExchangeRate(currency);
		BigDecimal totalAfterConversion = value.multiply(exchangeRate).setScale(2, RoundingMode.CEILING);
		
		return totalAfterConversion;
	}

}
