package com.fdmgroup.CurrencyConverter.model;

import java.util.List;

public interface CurrencyOptionData {
	public Currency getCurrency(String currencyCode, List<Currency> currencyOptions);
	public List<Currency> getCurrencyOptions();
}
