package com.fdmgroup.CurrencyConverter.view;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.CurrencyConverter.model.Currency;
import com.fdmgroup.CurrencyConverter.model.CurrencyOptionData;

@Component
public class InputController {
	private CurrencyOptionData co;
	
	@Autowired
	public InputController(CurrencyOptionData currencyOptionData) {
		this.co = currencyOptionData;
	}
	
	public boolean validateCurrencyOptions(int userCurrencyOptions) {
//		show options for currencies: 1 or 2
		if(userCurrencyOptions == 1 || userCurrencyOptions == 2) {
			return true;
		}
		else {
			System.out.println("You entered an invalid option. Please try again.");
			return false;
		}
	}
	
	public boolean validateCurrencyCode(String userCurrencyCode) {
		boolean isUpperCase = false;
		boolean currencyAvailable = false;
		
//		Validate if string is uppercase
		char[] charArray = userCurrencyCode.toCharArray();
		
        for(int i=0; i < charArray.length; i++){
            if(Character.isUpperCase(charArray[i])) {
                isUpperCase = true;
            }
        }
        
//        Validate if entered currency is available
        for(Currency c : co.getCurrencyOptions()) {
        	if(c.getCurrAbbreviation().contentEquals(userCurrencyCode)) {
        		currencyAvailable = true;
        	}
        }
        
//        Validate, if all requirements are reached
		if(userCurrencyCode.length() == 3 && isUpperCase && currencyAvailable) {
			return true;
		}
		else {
			System.out.println();
			System.out.println();
			System.out.println("You have entered an invalid currency code. Please try again.");
			System.out.println("Maybe it was not in upper case.");
			return false;
		}
	}
	
	public boolean validateCurrencyDirection(int userCurrencyDirection) {
		if(userCurrencyDirection == 1 || userCurrencyDirection == 2) {
			return true;
		}
		else {
			System.out.println("You entered an invalid option. Please try again.");
			return false;
		}
	}
	
	public boolean validateCurrencyValue(BigDecimal userCurrencyValue) {
		if(userCurrencyValue.compareTo(BigDecimal.ZERO) > 0) {
			return true;
		}
		else {
			System.out.println("You have entered a value that is not valid. Please try again.");
			return false;
		}
	}
}
