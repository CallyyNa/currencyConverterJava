package com.fdmgroup.CurrencyConverter.view;

import java.math.BigDecimal;

import com.fdmgroup.CurrencyConverter.Controller.ConverterController;

public interface IView {
	void display(BigDecimal bd);
	
	void getInput();
	
	void displayWelcome();
	
	void setController(ConverterController cc);
}
