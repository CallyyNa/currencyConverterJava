package com.fdmgroup.CurrencyConverter.model;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class URLCurrencyData implements CurrencyData {
	private String url;
	Map<String, BigDecimal> currencyMap = new HashMap<String, BigDecimal>();
	Document doc;
	
	private void readUrl() {
		String currencyCode = null;
		BigDecimal convertedRate = null;
		url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new URL(url).openStream());
			
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Cube");

			for (int temp = 2; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				NamedNodeMap currencyNodeList = nNode.getAttributes();

				for (int i = 0; i < currencyNodeList.getLength(); i++) {
					Node currencyNode = currencyNodeList.item(i);
					
					if (currencyNode.getNodeName() == "currency") {
						currencyCode = currencyNode.getNodeValue();
					}
					if (currencyNode.getNodeName() == "rate") {
						convertedRate = new BigDecimal(currencyNode.getNodeValue());
					}
					currencyMap.put(currencyCode, convertedRate);
		
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public BigDecimal getExchangeRate(String currency) {
		readUrl();
		BigDecimal exRateString = currencyMap.get(currency);
		return exRateString;
	}

}
