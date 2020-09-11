package com.fdmgroup.CurrencyConverter.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public interface Converter {
	BigDecimal convertToEUR(String s, BigDecimal bd);
	BigDecimal convertFromEUR(String s, BigDecimal bd);
}
