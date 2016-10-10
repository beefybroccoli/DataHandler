package Utilities.XML;

import Model.GenericObject;
import Model.Issue;
import Model.LongBox;
import Model.Person;
import Model.Publisher;
import Model.StoryArc;
import Model.Volume;
import Utilities.XML.FileName;
import static Utilities.XML.XMLQuery.extractFromNodeList;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDatabase_Incomplete {

    private LongBox<GenericObject> mPublisher;
    private LongBox<GenericObject> mPerson;
    private LongBox<GenericObject> mVolume;
    private LongBox<GenericObject> mIssue;
    private LongBox<GenericObject> mStoryarc;

    public XMLDatabase_Incomplete() {
        mPublisher = new LongBox<GenericObject>();
        mPerson = new LongBox<GenericObject>();
        mVolume = new LongBox<GenericObject>();
        mIssue = new LongBox<GenericObject>();
        mStoryarc = new LongBox<GenericObject>();

    }

    public XMLDatabase_Incomplete(String publisherFile, String personFile, String VolumeFile, String issueFile, String storyarcFile) {
        mPublisher = new LongBox<GenericObject>();
        mPerson = new LongBox<GenericObject>();
        mVolume = new LongBox<GenericObject>();
        mIssue = new LongBox<GenericObject>();
        mStoryarc = new LongBox<GenericObject>();

        try {
            generateDataBase(publisherFile, personFile, VolumeFile, issueFile, storyarcFile);
        } catch (Exception ex) {
            Logger.getLogger(XMLDatabase_Incomplete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateDataBase() {
        try {
            mPublisher = Utilities.XML.SAXGenericHandler.readXML(FileName.FileSamplePublishers);
            mPerson = Utilities.XML.SAXGenericHandler.readXML(FileName.FileSamplePersons);
            mVolume = Utilities.XML.SAXGenericHandler.readXML(FileName.FileSampleVolumes);
            mIssue = Utilities.XML.SAXGenericHandler.readXML(FileName.FileSampleIssues);
            mStoryarc = Utilities.XML.SAXGenericHandler.readXML(FileName.FileSampleStoryArcs);
        } catch (Exception ex) {
            Logger.getLogger(XMLDatabase_Incomplete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateDataBase(String publisherFile, String personFile, String VolumeFile, String issueFile, String storyarcFile) {
        try {
            mPublisher = Utilities.XML.SAXGenericHandler.readXML(publisherFile);
            mPerson = Utilities.XML.SAXGenericHandler.readXML(personFile);
            mVolume = Utilities.XML.SAXGenericHandler.readXML(VolumeFile);
            mIssue = Utilities.XML.SAXGenericHandler.readXML(issueFile);
            mStoryarc = Utilities.XML.SAXGenericHandler.readXML(storyarcFile);
        } catch (Exception ex) {
            Logger.getLogger(XMLDatabase_Incomplete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String toString() {
        String result = "";

        result = "database contains "
                + mPublisher.size() + " publishers"
                + ", " + mPerson.size() + " persons"
                + ", " + mVolume.size() + " volumes"
                + ", " + mIssue.size() + " issues"
                + " and " + mStoryarc.size() + " storyarcs.";
        return result;
    }

    public LongBox<GenericObject> getPublishers() {
        return mPublisher;
    }

    public LongBox<GenericObject> getIssues() {
        return mIssue;
    }

    public LongBox<GenericObject> getPersons() {
        return mPerson;
    }

    public LongBox<GenericObject> getStoryarcs() {
        return mStoryarc;
    }

    public LongBox<GenericObject> getVolumes() {
        return mVolume;
    }

    /*
    addObject function cause error
     */
    public static void addObject(String inputFileName, GenericObject obj) throws XPathExpressionException, TransformerException {

        try {
            //System.out.println("----------------------------------------------------------");
            // Create a document by parsing a XML file
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(inputFileName));

            // Get a node using XPath
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/publishers";
            Node node = (Node) xPath.evaluate(expression, document, XPathConstants.NODE);

            // Set the node content
            //node.setTextContent("<publisher>\n<name>social engineering team</name>\n<id>6</id></publisher>\n");
            // Write changes to a file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new File(inputFileName)));

        } catch (ParserConfigurationException e) {
            System.out.println("Parse Configuration Error");
            //e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("SAX Error");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("file not found");
        }

    }

    public static void removeObject(GenericObject obj) {

    }

    public static void modifyObject(GenericObject obj) {

    }

    public static void main(String[] args) throws XPathExpressionException, TransformerException {
        System.out.println("---------------------XMLDatabase test----------------------");
        XMLDatabase_Incomplete sampleDatabase = new XMLDatabase_Incomplete(FileName.FileSamplePublishers, FileName.FileSamplePersons, FileName.FileSampleVolumes, FileName.FileSampleIssues, FileName.FileSampleStoryArcs);
        System.out.println(sampleDatabase.toString());

        LongBox<GenericObject> box = sampleDatabase.getPublishers();
        for (GenericObject element : box.getArray()) {
            Publisher publisher = (Publisher) element;
            System.out.println(publisher.toString());
        }
        System.out.println("");

        box = sampleDatabase.getIssues();
        for (GenericObject element : box.getArray()) {
            Issue publisher = (Issue) element;
            System.out.println(publisher.toString());
        }
        System.out.println("");

        box = sampleDatabase.getPersons();
        for (GenericObject element : box.getArray()) {
            Person publisher = (Person) element;
            System.out.println(publisher.toString());
        }
        System.out.println("");

        box = sampleDatabase.getStoryarcs();
        for (GenericObject element : box.getArray()) {
            StoryArc publisher = (StoryArc) element;
            System.out.println(publisher.toString());
        }
        System.out.println("");

        box = sampleDatabase.getVolumes();
        for (GenericObject element : box.getArray()) {
            Volume publisher = (Volume) element;
            System.out.println(publisher.toString());
        }
        System.out.println("");

    }
}
