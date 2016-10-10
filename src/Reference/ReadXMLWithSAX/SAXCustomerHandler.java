package Reference.ReadXMLWithSAX;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXCustomerHandler extends DefaultHandler {

	private List<Customer> data;
	private Customer customer;
	private String currentElement = "";
	private StringBuilder currentText;
	
	private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public List<Customer> readDataFromXML(String filename) throws IOException, ParserConfigurationException {
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			parser.parse(new File(filename), this);
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		}
		
		return data;
	}
	
	@Override
	public void startDocument() throws SAXException {
//		System.out.println("Start document");
		data = new ArrayList<>();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("End document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
//		System.out.println("Start element: " + qName);
		currentElement = qName;
		
		switch (currentElement) {
		case "customers":
			break;

		case "customer":
			customer = new Customer();
			String idAsString = attributes.getValue(Customer.ID);
			customer.setId(Integer.parseInt(idAsString));
			data.add(customer);
			break;
			
		default:
			currentText = new StringBuilder();
			break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
//		System.out.println("End element: " + qName);
		
		if (currentElement.equals("customers") || currentElement.equals("customer")) {
			return;
		}
		
		String content = currentText.toString();
		
		switch (currentElement) {
		case Customer.NAME:
			customer.setName(content);
			break;

		case Customer.PHONE:
			customer.setPhone(content);
			break;

		case Customer.ABOUT:
			customer.setAbout(content);
			break;

		case Customer.AGE:
			customer.setAge(Integer.parseInt(content));
			break;

		case Customer.ACTIVE:
			customer.setActive(Boolean.parseBoolean(content));
			break;
			
		case Customer.BALANCE:
			customer.setBalance(new BigDecimal(content));
			break;

		case Customer.JOINED:
			DateFormat df = new SimpleDateFormat(XMLDATEFORMAT);
			try {
				customer.setJoined(df.parse(content));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		
		currentElement = "";
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (currentText != null) {
			currentText.append(ch, start, length);
		}
//		System.out.println("Characters");
	}
	
	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.out.println("warning");
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("error");
	}
	
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("fatal error");
	}
	
}
