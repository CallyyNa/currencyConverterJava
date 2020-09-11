package com.fdmgroup.CurrencyConverter.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public interface CurrencyData {
	BigDecimal getExchangeRate(String s);
}
