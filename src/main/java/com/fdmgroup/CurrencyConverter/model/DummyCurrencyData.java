package com.fdmgroup.CurrencyConverter.model;

import java.math.BigDecimal;

public class DummyCurrencyData implements CurrencyData {

	public DummyCurrencyData() {
		System.out.println("DummyCurrencyData");
	}
	
	public BigDecimal getExchangeRate(String s) {
		return null;
	}

}
