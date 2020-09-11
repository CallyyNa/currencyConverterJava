package com.fdmgroup.CurrencyConverter.model;

public class Currency {
	private String currAbbreviation;
	private String symbol;
	private String currWord;

	public String getCurrAbbreviation() {
		return currAbbreviation;
	}

	public void setCurrAbbreviation(String currAbbreviation) {
		this.currAbbreviation = currAbbreviation;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrWord() {
		return currWord;
	}

	public void setCurrWord(String currWord) {
		this.currWord = currWord;
	}
}
