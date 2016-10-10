package Reference.ReadXMLWithSAX;

import Utilities.SAXGenericHandler;
import Model.LongBox;
import Model.Volume;
import Model.StoryArc;
import Model.Issue;
import Model.GenericObject;
import Model.Publisher;
import Model.Person;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

public class ReadXMLWithSAX {

    public static void main(String[] args) throws Exception {
        readVolumesXML();
        readPublishersXML();
        readPersonsXML();
        readStoryarcsXML();
        readIssuesXML();

    }

    public static void readCustomerXML() throws Exception {
        System.out.println("(start ReadXMLWithSax and handle error)");

        String filename = "data/customers.xml";

        SAXCustomerHandler saxHandler = new SAXCustomerHandler();
        List<Customer> data = saxHandler.readDataFromXML(filename);
        System.out.println("Number of customers: " + data.size());

        for (Customer customer : data) {
            System.out.println(customer);
        }
        System.out.println("(end ReadXMLWithSax and handle error)");
    }

    public static void readPublishersXML() throws IOException, ParserConfigurationException {
        System.out.println("(start readPublishersXML)");
        String filename = "data/xml_publishers.xml";
        SAXGenericHandler saxHandler = new SAXGenericHandler();

        LongBox<GenericObject> data = saxHandler.readDataFromXML(filename);
        System.out.println("size of data is " + data.size());
        for (GenericObject element : data.getArray()) {
            Publisher temp = (Publisher) element;
            System.out.println(temp.toString());
        }

        System.out.println("(end readPublishersXML)\n");
    }

    public static void readPersonsXML() throws IOException, ParserConfigurationException {
        System.out.println("(start readPersonsXML)");
        String filename = "data/xml_persons.xml";
        SAXGenericHandler saxHandler = new SAXGenericHandler();

        LongBox<GenericObject> data = saxHandler.readDataFromXML(filename);
        System.out.println("size of data is " + data.size());
        for (GenericObject element : data.getArray()) {
            Person temp = (Person) element;
            System.out.println(temp.toString());
        }

        System.out.println("(end readPersonsXML)\n");
    }

    public static void readStoryarcsXML() throws IOException, ParserConfigurationException {

        System.out.println("(start readStoryarcsXML)");
        String filename = "data/xml_storyarcs.xml";
        SAXGenericHandler saxHandler = new SAXGenericHandler();

        LongBox<GenericObject> data = saxHandler.readDataFromXML(filename);
        System.out.println("size of data is " + data.size());

        int i = 0;
        for (GenericObject element : data.getArray()) {
            StoryArc temp = (StoryArc) element;
            System.out.println(temp.toString());
        }

        System.out.println("(end readStoryarcsXML)\n");
    }

    public static void readVolumesXML() throws IOException, ParserConfigurationException {
        System.out.println("(start readVolumesXML)");
        String filename = "data/xml_volumes.xml";
        SAXGenericHandler saxHandler = new SAXGenericHandler();

        LongBox<GenericObject> data = saxHandler.readDataFromXML(filename);
        System.out.println("size of data is " + data.size());

        int i = 0;
        for (GenericObject element : data.getArray()) {
            Volume temp = (Volume) element;
            System.out.println(temp.toString());
        }

        System.out.println("(end readVolumesXML)\n");
    }

    public static void readIssuesXML() throws ParserConfigurationException, IOException {
        System.out.println("(start readIssuesXML)");
        String filename = "data/xml_issues.xml";
        SAXGenericHandler saxHandler = new SAXGenericHandler();

        LongBox<GenericObject> data = saxHandler.readDataFromXML(filename);
        System.out.println("size of data is " + data.size());

        int i = 0;
        for (GenericObject element : data.getArray()) {
            Issue temp = (Issue) element;
            System.out.println(temp.toString());
        }

        System.out.println("(end readIssuesXML)\n");

    }
}
