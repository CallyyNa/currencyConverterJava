package com.fdmgroup.CurrencyConverter.model;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class XMLCurrencyData implements CurrencyData {
	private String xmlFile;
	private Map<String, BigDecimal> currencyMap = new HashMap<String, BigDecimal>();
	private String currencyCode;
	private BigDecimal convertedRate;
//	private Document doc;

	public XMLCurrencyData() {}

	private void readFile() {
		xmlFile = "src/main/resources/currencies.xml";

		try {
			File inputFile = new File(xmlFile);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BigDecimal getExchangeRate(String currency) {
		readFile();
		BigDecimal exRateString = currencyMap.get(currency);
		return exRateString;
	}
}
