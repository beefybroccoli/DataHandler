package Utilities.XML;

import Model.LongBox;
import Model.StoryArc;
import Model.Volume;
import Model.Issue;
import Model.GenericObject;
import Model.Publisher;
import Model.Person;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import java.lang.StringBuilder;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXGenericHandler extends DefaultHandler {

    private LongBox<GenericObject> data;
    private Publisher publisher;
    private Person person;
    private Volume volume;
    private StoryArc storyarc;
    private Issue issue;
    private String currentElement = "";
    private StringBuilder currentText;
    private String currentObject;
    private boolean cdataBoolean = false;

    private static final String XMLDATEFORMAT = "yyyy-MM-dd";

    public LongBox<GenericObject> readDataFromXML(String filename) throws IOException, ParserConfigurationException {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            data = new LongBox<GenericObject>();

            //System.out.println("*size of data is " + data.size());
            parser.parse(new File(filename), this);
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    @Override
    public void startDocument() throws SAXException {
        //System.out.println("*Start document");
    }

    @Override
    public void endDocument() throws SAXException {
        //System.out.println("*End document");
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        //System.out.println("Start element: " + qName);
        currentElement = qName;

        switch (currentElement) {
            case "publishers":
                currentObject = "publishers";
                break;

            case "publisher":
                publisher = new Publisher();
                data.add((GenericObject) publisher);
                break;

            case "persons":
                currentObject = "persons";
                break;

            case "person":
                person = new Person();
                data.add((GenericObject) person);
                break;

            case "volumes":
                currentObject = "volumes";
                break;

            case "volume":
                volume = new Volume();
                data.add((GenericObject) volume);
                break;

            case "storyarcs":
                currentObject = "storyarcs";
                break;

            case "storyarc":
                storyarc = new StoryArc();
                data.add((GenericObject) storyarc);
                break;

            case "issues":
                currentObject = "issues";
                break;

            case "issue":
                issue = new Issue();
                //System.out.println(volume.toString());
                data.add((GenericObject) issue);
                //Volume temp = (Volume) data.getLastElement();
                //System.out.println(temp.toString());
                break;

            default:
                currentText = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        String content = currentText.toString();

        switch (currentElement) {
            case "![CDATA[%s]]":
                cdataBoolean = true;
                break;

            case "name":
                assignValue("name", content);
                break;

            case "id":
                assignValue("id", content);
                break;

            case "count_of_issues":
                assignValue("count_of_issues", content);
                break;

            case "description":
                StringBuilder stringBuilder = new StringBuilder();
                content = stringBuilder.toString();
                assignValue("description", content);
                break;

            case "publisher_name":
                assignValue("publisher_name", content);
                break;

            case "issue_number":
                assignValue("issue_number", content);
                break;

            case "site_detail_url":
                assignValue("site_detail_url", content);
                break;

            case "store_date":
                assignValue("store_date", content);
                break;

            case "cover_date":
                assignValue("cover_date", content);
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

    public void assignValue(String inputVariableName, String inputValue) {

        if (currentObject.equals("publishers")) {
            switch (inputVariableName) {
                case "name":
                    publisher.setName(inputValue);
                    break;

                case "id":
                    publisher.setId(inputValue);
                    break;
                default:
                    break;
            }
        }//end if
        else if (currentObject.equals("persons")) {
            switch (inputVariableName) {
                case "name":
                    //publisher.setName(content);
                    person.setName(inputValue);
                    break;

                case "id":
                    person.setId(inputValue);
                    break;
                default:
                    break;
            }
        }//end if
        else if (currentObject.equals("volumes")) {

            //System.out.println("* currentObject " + currentObject 
            //        + ", should assign " + inputValue + " to " + inputVariableName);
            switch (inputVariableName) {
                case "name":
                    volume.setName(inputValue);
                    break;

                case "id":
                    volume.setId(inputValue);

                    break;
                case "count_of_issues":
                    volume.setCount_of_issues(inputValue);
                    break;

                case "description":
                    String temp = new String(inputValue.toCharArray(), 0, inputValue.length());
                    volume.setDescription(inputValue);
                    break;
                case "publisher_name":
                    volume.setPublisher_name(inputValue);
                    break;

                default:
                    break;
            }
        }//end if
        else if (currentObject.equals("storyarcs")) {

            //System.out.println("* currentObject " + currentObject 
            //        + ", should assign " + inputValue + " to " + inputVariableName);
            switch (inputVariableName) {
                case "name":
                    storyarc.setName(inputValue);
                    break;
                case "id":
                    storyarc.setId(inputValue);
                    break;
                case "publisher_name":
                    storyarc.setPublisher_name(inputValue);
                    break;

                default:
                    break;
            }
        }//end if
        else if (currentObject.equals("issues")) {
            switch (inputVariableName) {
                case "id":
                    issue.setId(inputValue);
                    break;
                case "image_location":
                    issue.setImage_location(inputValue);
                    break;
                case "issue_number":
                    issue.setIssue_number(inputValue);
                    break;
                case "name":
                    issue.setName(inputValue);
                    break;
                case "site_detail_url":
                    issue.setSite_detail_url(inputValue);
                    break;
                case "store_date":
                    issue.setStore_date(inputValue);
                    break;
                case "cover_date":
                    issue.setCover_date(inputValue);
                    break;
                case "description":
                    issue.setDescription(inputValue);
                    break;

                default:
                    break;
            }
        }//end if

    }// assign value

    public static LongBox<GenericObject> readXML(String inputFileName) throws Exception {

        //String filename = "data/customers.xml";
        SAXGenericHandler saxHandler = new SAXGenericHandler();
        LongBox<GenericObject> data = saxHandler.readDataFromXML(inputFileName);

        return data;

    }

    public static void main(String[] args) throws Exception {

        String[] inputName = {
            FileName.FileSamplePublishers, FileName.FileSampleIssues, FileName.FileSamplePersons, FileName.FileSampleStoryArcs, FileName.FileSampleVolumes
        };

        for (int i = 0; i < inputName.length; i++) {
            LongBox<GenericObject> box = SAXGenericHandler.readXML(inputName[i]);
            System.out.println("box size is " + box.size());
        }

    }

//
//    public static void main(String[] args) throws Exception {
//        readVolumesXML();
//        readPublishersXML();
//        readPersonsXML();
//        readStoryarcsXML();
//        readIssuesXML();
//
//    }
//
//    public static void readCustomerXML() throws Exception {
//        System.out.println("(start ReadXMLWithSax and handle error)");
//
//        String filename = "data/customers.xml";
//
//        SAXCustomerHandler saxHandler = new SAXCustomerHandler();
//        List<Customer> data = saxHandler.readDataFromXML(filename);
//        System.out.println("Number of customers: " + data.size());
//
//        for (Customer customer : data) {
//            System.out.println(customer);
//        }
//        System.out.println("(end ReadXMLWithSax and handle error)");
//    }
//
//    public static void readPublishersXML() throws IOException, ParserConfigurationException {
//        System.out.println("(start readPublishersXML)");
//        String filename = "data/xml_publishers.xml";
//        SAXGenericHandler saxHandler = new SAXGenericHandler();
//
//        LongBox<ModelGeneric> data = saxHandler.readDataFromXML(filename);
//        System.out.println("size of data is " + data.size());
//        for (GenericObject element : data.getArray()) {
//            Publisher temp = (Publisher) element;
//            System.out.println(temp.toString());
//        }
//
//        System.out.println("(end readPublishersXML)\n");
//    }
//
//    public static void readPersonsXML() throws IOException, ParserConfigurationException {
//        System.out.println("(start readPersonsXML)");
//        String filename = "data/xml_persons.xml";
//        SAXGenericHandler saxHandler = new SAXGenericHandler();
//
//        LongBox<ModelGeneric> data = saxHandler.readDataFromXML(filename);
//        System.out.println("size of data is " + data.size());
//        for (GenericObject element : data.getArray()) {
//            Person temp = (Person) element;
//            System.out.println(temp.toString());
//        }
//
//        System.out.println("(end readPersonsXML)\n");
//    }
//
//    public static void readStoryarcsXML() throws IOException, ParserConfigurationException {
//
//        System.out.println("(start readStoryarcsXML)");
//        String filename = "data/xml_storyarcs.xml";
//        SAXGenericHandler saxHandler = new SAXGenericHandler();
//
//        LongBox<ModelGeneric> data = saxHandler.readDataFromXML(filename);
//        System.out.println("size of data is " + data.size());
//
//        int i = 0;
//        for (GenericObject element : data.getArray()) {
//            StoryArc temp = (StoryArc) element;
//            System.out.println(temp.toString());
//        }
//
//        System.out.println("(end readStoryarcsXML)\n");
//    }
//
//    public static void readVolumesXML() throws IOException, ParserConfigurationException {
//        System.out.println("(start readVolumesXML)");
//        String filename = "data/xml_volumes.xml";
//        SAXGenericHandler saxHandler = new SAXGenericHandler();
//
//        LongBox<ModelGeneric> data = saxHandler.readDataFromXML(filename);
//        System.out.println("size of data is " + data.size());
//
//        int i = 0;
//        for (GenericObject element : data.getArray()) {
//            Volume temp = (Volume) element;
//            System.out.println(temp.toString());
//        }
//
//        System.out.println("(end readVolumesXML)\n");
//    }
//
//    public static void readIssuesXML() throws ParserConfigurationException, IOException {
//        System.out.println("(start readIssuesXML)");
//        String filename = "data/xml_issues.xml";
//        SAXGenericHandler saxHandler = new SAXGenericHandler();
//
//        LongBox<ModelGeneric> data = saxHandler.readDataFromXML(filename);
//        System.out.println("size of data is " + data.size());
//
//        int i = 0;
//        for (GenericObject element : data.getArray()) {
//            Issue temp = (Issue) element;
//            System.out.println(temp.toString());
//        }
//
//        System.out.println("(end readIssuesXML)\n");
//
//    }
}//end class
