/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference.SearchThroughXML;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XML_Query_SAX_1 {

    public static void querySAX(String inputFileName) {
        try {

            //File inputFile = new File(inputFileName);
            File inputFile = new File("data/input_car_xml.xml");

            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);

            System.out.println("Root element :" + document.getRootElement().getName());

            Element rootElement = document.getRootElement();

            System.out.println("---------------------------------------------");

            List<Element> supercarList = rootElement.getChildren("supercars");

            System.out.println("size of supercarList is " + supercarList.size());
            System.out.println("");

            for (int i = 0; i < supercarList.size(); i++) {
                Element supercarElement = supercarList.get(i);
                System.out.println("Current Element :" + supercarElement.getName());
                Attribute attribute = supercarElement.getAttribute("company");
                System.out.println("company : " + attribute.getValue());
                List<Element> carNameList = supercarElement.getChildren("carname");
                for (int j = 0; j < carNameList.size(); j++) {
                    Element carElement = carNameList.get(j);
                    String carName = carElement.getText();
                    Attribute typeAttribute = carElement.getAttribute("type");
                    String carType = (typeAttribute != null ? typeAttribute.getValue() : "null");
                    System.out.println("car name : " + carName + ", " + "car type : " + carType);
                }
                //deck
                Element deckElement = supercarElement.getChild("deck");
                //Attribute nullAttribute = deckElement.getAttribute("null");
                //System.out.println("* " + nullAttribute.toString());
                //String deckString = (nullAttribute != null ? "null" : deckElement.getText());
                System.out.println("deck : " + deckElement.getText());
                
                 
                Element aliasesElement = supercarElement.getChild("aliases");
                Attribute nullAttribute = aliasesElement.getAttribute("null");
                //String aliasText = (nullAttribute.getValue().equals("true") ? "null" : aliasesElement.getText());
                String aliasText = (nullAttribute == null ? aliasesElement.getText() :  "null" );
                System.out.println("aliasesElement : " + aliasText);
                
                System.out.println("");
            }
            
                        
            List<Element> luxurycarList = rootElement.getChildren("luxurycars");
            
            System.out.println("size of luxurycarList is " + luxurycarList.size());
            System.out.println("");
            
            for( int i =0 ; i < luxurycarList.size(); i++) {
                Element luxurycarElement = luxurycarList.get(i);
                System.out.println("Current Element :" + luxurycarElement.getName());
                List<Element> carNameList = luxurycarElement.getChildren("carname");
                for ( int j = 0; j < carNameList.size();j++) {
                    Element carElement = carNameList.get(j);
                    String carName = carElement.getText();
                    System.out.println("car name : " + carName);
                    
                }
            }
            
            
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        querySAX("");
    }

}

/*
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<cars>
   <supercars company="Ferrari">
      <carname type="formula one">Ferrari 101</carname>
      <carname type="sports">Racing Car</carname>
      <deck null="true" />
      <cover_date>2014-02-01</cover_date>
      <name>Mary Jane</name>
   </supercars>
   <supercars company="Hondai">
      <carname type="formula two">Ferrari 101</carname>
      <carname type="sports">Racing Car</carname>
      <deck>information about deck<deck/>
      <cover_date>2014-02-01</cover_date>
      <name>James Band</name>
   </supercars>
   <luxurycars company="Benteley">
      <carname>Benteley 1</carname>
      <carname>Benteley 2</carname>
      <carname>Benteley 3</carname>
   </luxurycars>
</cars>
*/
