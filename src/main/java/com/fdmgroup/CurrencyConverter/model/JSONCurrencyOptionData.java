package com.fdmgroup.CurrencyConverter.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONCurrencyOptionData  implements CurrencyOptionData {

	public JSONCurrencyOptionData() {
		System.out.println("json bla bla");
	}
	
	public Currency getCurrency(String currencyCode, List<Currency> currencyList){
		Currency foundCurrency = new Currency();
		
		for(Currency c : currencyList) {
			if(c.getCurrAbbreviation().equals(currencyCode)) {
				foundCurrency.setCurrAbbreviation(c.getCurrAbbreviation());
				foundCurrency.setCurrWord(c.getCurrWord());
				foundCurrency.setSymbol(c.getSymbol());
			}
		}
		
		return foundCurrency;
	}

	public List<Currency> getCurrencyOptions() {
		List<Currency> currencyOptions = new ArrayList<Currency>();
		try {
			JSONParser parser = new JSONParser();
			JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/resources/currencies.json"));
			
			  for (Object o : a)
			  {
			    JSONObject currencyObject = (JSONObject) o;

			    String currAbbreviation = (String) currencyObject.get("currAbbreviation");
			    String symbol = (String) currencyObject.get("symbol");
			    String currWord = (String) currencyObject.get("currWord");
			    
			    Currency newCurrency = new Currency();
			    newCurrency.setCurrAbbreviation(currAbbreviation);
			    newCurrency.setCurrWord(currWord);
			    newCurrency.setSymbol(symbol);
			    
			    currencyOptions.add(newCurrency);
			  }
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		return currencyOptions;
	}

}
