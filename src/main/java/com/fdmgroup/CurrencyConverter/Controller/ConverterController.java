package com.fdmgroup.CurrencyConverter.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.CurrencyConverter.model.Converter;
import com.fdmgroup.CurrencyConverter.model.Direction;
import com.fdmgroup.CurrencyConverter.view.IView;

@Component
public class ConverterController {
	private String currency;
	private BigDecimal value;
	
	private Converter converter;
	private IView iview;
	
	private Direction direction;
	
	public ConverterController() {}
	
	
	public ConverterController(Converter theConverter) {
		this.converter = theConverter;
	}
	
	@Autowired
	public ConverterController(Converter theConverter, IView theIview) {
		this.converter = theConverter;
		this.iview = theIview;
	}
	
	public void startup() {
		iview.setController(this);
		iview.displayWelcome();
		iview.getInput();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction theDirection) {
		this.direction = theDirection;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public void convert() {		
		if(direction == Direction.TOEUR) {
			setValue(converter.convertToEUR(currency, value));
		}
		else if(direction == Direction.FROMEUR) {
			setValue(converter.convertFromEUR(currency, value));
		}
		iview.display(value);
	}	
}
