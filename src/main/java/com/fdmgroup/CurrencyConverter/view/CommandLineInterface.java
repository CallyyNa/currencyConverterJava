package com.fdmgroup.CurrencyConverter.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.CurrencyConverter.Controller.ConverterController;
import com.fdmgroup.CurrencyConverter.model.Currency;
import com.fdmgroup.CurrencyConverter.model.CurrencyOptionData;
import com.fdmgroup.CurrencyConverter.model.Direction;

@Component
public class CommandLineInterface implements IView {
	private ConverterController cc;
	
	private CurrencyOptionData currencyOptionData;
	
	private Currency currencyDescription;
	private List<Currency> currencyOptions = new ArrayList<Currency>();
	
	@Autowired
	public CommandLineInterface(CurrencyOptionData currOption) {
		System.out.println("crating currency options");
		this.currencyOptionData = currOption;
	}
	
	public void display(BigDecimal value) {
		System.out.println();
		System.out.println();
		
		if(cc.getDirection() == Direction.FROMEUR) {
			System.out.println("We have converted Euro to " + currencyDescription.getCurrWord());
			System.out.println("You will receive " + value + " " + currencyDescription.getSymbol());
		}
		if(cc.getDirection() == Direction.TOEUR) {
			System.out.println("We have converted your " + currencyDescription.getCurrWord() + " to Euro");
			System.out.println("You will receive " + value + " €");
		}
		
	}

	public void getInput() {
		InputController ic = new InputController(currencyOptionData);
		Scanner sc = new Scanner(System.in);
		
		String userInputCurrency = null;
		int userInputCurrencyOptions;
		int userInputDirection;
		BigDecimal userInputValue;
		
		System.out.println("Which currency would you like to convert today?");
		
		System.out.println();
		System.out.println();
		
		System.out.println("Do you want to see the options you have? Yes (1) No (2)");
		
		userInputCurrencyOptions = sc.nextInt();
		
//		Validate see options of currencies
		if(ic.validateCurrencyOptions(userInputCurrencyOptions)) {
			switch(userInputCurrencyOptions) {
			case 1:
				for(Currency c : currencyOptions) {
					System.out.println(c.getCurrWord() + ": " + c.getCurrAbbreviation());
				}
			default: 
				System.out.println();
				System.out.println();
				
				System.out.println("Please enter the currency code in upper case:");
				userInputCurrency = sc.next();
				setCurrencyDescription(currencyOptionData.getCurrency(userInputCurrency, currencyOptions));
				break;
			}
//			validate input currency code
			if(ic.validateCurrencyCode(userInputCurrency)) {
				System.out.println();
				System.out.println();
				
				System.out.println("Would you like to convert to Euro (1) or from Euro (2)?");
				userInputDirection = sc.nextInt();
				
//				Validate input conversion direction
				if(ic.validateCurrencyDirection(userInputDirection)) {
					
					System.out.println();
					System.out.println();
					
					if(userInputDirection == 1) {
						System.out.println("How much of " + currencyDescription.getCurrWord() + " would you like to convert to Euro?");
					}
					else if(userInputDirection == 2) {
						System.out.println("How much Euro would you like to convert to " + currencyDescription.getCurrWord() + "?");
					}
					
					System.out.println("Please use a comma instead of period when using decimals.");
					userInputValue = sc.nextBigDecimal();
					
//					Validate input of amount to convert
					if(ic.validateCurrencyValue(userInputValue)) {
						cc.setValue(userInputValue);
						cc.setCurrency(userInputCurrency);
						
						if(userInputDirection == 1) {
							cc.setDirection(Direction.TOEUR);
						}
						else if(userInputDirection == 2) {
							cc.setDirection(Direction.FROMEUR);
						}
					}
				}
			}	
		}
		sc.close();
	}

	public void displayWelcome() {
		setCurrencyOptions(currencyOptionData.getCurrencyOptions());
		
		System.out.println("Welcome to my currency converter!");
	}
	
	public void setController(ConverterController controller) {
		this.cc = controller;
	}

	public void setCurrencyDescription(Currency currencyDescription) {
		this.currencyDescription = currencyDescription;
	}

	public void setCurrencyOptions(List<Currency> currencyOptions) {
		this.currencyOptions = currencyOptions;
	}
}
