/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities.XML;

import Model.*;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;



public class XMLQuery {

    public QueryString mQueryString = new QueryString();

    /*
    reference : http://www.w3schools.com/xsl/xpath_syntax.asp
     */
    private static void main(String[] args) throws Exception {
        runQueries();
    }

    public static void runQueries() throws Exception {

        query(QueryString.AllPublisher, FileName.FileSamplePublishers);

        query(QueryString.AllPerson, FileName.FileSamplePersons);

        query(QueryString.AllIssue, FileName.FileSampleIssues);

        query(QueryString.AllVolume, FileName.FileSampleVolumes);

        query(QueryString.AllStoryArc, FileName.FileSampleStoryArcs);
    }

    public static LongBox<GenericObject> query(String inpuTexpression, String inputFileName) throws Exception {
        LongBox<GenericObject> extractedLongBox = new LongBox<GenericObject>();

        try {
            //System.out.println("----------------------------------------------------------");
            File inputFile = new File(inputFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList nodeList = (NodeList) xPath.compile(inpuTexpression).evaluate(doc, XPathConstants.NODESET);

            System.out.println("size of nodeList is " + nodeList.getLength());

            extractedLongBox = extractFromNodeList(nodeList, "publisher");

            System.out.println("size of extractedLongBox is " + extractedLongBox.size() + "\n");

        } catch (ParserConfigurationException e) {
            System.out.println("Parse Configuration Error");
            //e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("SAX Error");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("file not found");
        } catch (XPathExpressionException e) {
            System.out.println("XPath Expression Error");
            //e.printStackTrace();
        }

        return extractedLongBox;
    }

    public static LongBox<GenericObject> extractFromNodeList(NodeList nodeList, String objectType) {
        LongBox<GenericObject> result = new LongBox<GenericObject>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            //System.out.println("\nCurrent Element :" + nNode.getTextContent());

            switch (objectType) {
                case "publisher":
                    Publisher publisher = extractPublisherFromNodeList(nNode);
                    result.add(publisher);
                    break;

                case "person":
                    Person person = extractPersonFromNodeList(nNode);
                    result.add(person);
                    break;

                case "issue":
                    Issue issue = extractIssueFromNodeList(nNode);
                    result.add(issue);
                    break;

                case "volume":
                    Volume volume = extractVolumeFromNodeList(nNode);
                    result.add(volume);
                    break;

                case "storyarc":
                    StoryArc storyArc = extractStoryArcFromNodeList(nNode);
                    result.add(storyArc);
                    break;

                default:
                    throw new AssertionError();
            }

            Publisher publisher = extractPublisherFromNodeList(nNode);
            System.out.println(publisher.toString());
        }//end for loop

        return result;
    }

    private static StoryArc extractStoryArcFromNodeList(Node nNode) {
        StoryArc result = new StoryArc();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            String name = eElement.getElementsByTagName("name").item(0).getTextContent();
            String id = eElement.getElementsByTagName("id").item(0).getTextContent();
            String publisher_name = eElement.getElementsByTagName("publisher_name").item(0).getTextContent();

            result.setName(name);
            result.setId(id);
            result.setPublisher_name(publisher_name);
        }
        return result;

    }

    private static Volume extractVolumeFromNodeList(Node nNode) {
        Volume result = new Volume();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            String name = eElement.getElementsByTagName("name").item(0).getTextContent();
            String id = eElement.getElementsByTagName("id").item(0).getTextContent();
            String description = eElement.getElementsByTagName("description").item(0).getTextContent();
            String count_of_issues = eElement.getElementsByTagName("count_of_issues").item(0).getTextContent();
            String publisher_name = eElement.getElementsByTagName("publisher_name").item(0).getTextContent();

            result.setName(name);
            result.setId(id);
            result.setDescription(description);
            result.setCount_of_issues(count_of_issues);
            result.setPublisher_name(publisher_name);
        }
        return result;
    }

    private static Publisher extractPublisherFromNodeList(Node nNode) {
        Publisher result = new Publisher();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            String name = eElement.getElementsByTagName("name").item(0).getTextContent();
            String id = eElement.getElementsByTagName("id").item(0).getTextContent();
            result.setName(name);
            result.setId(id);
        }
        return result;
    }

    private static Person extractPersonFromNodeList(Node nNode) {
        Person result = new Person();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            String name = eElement.getElementsByTagName("name").item(0).getTextContent();
            String id = eElement.getElementsByTagName("id").item(0).getTextContent();
            result.setName(name);
            result.setId(id);
        }
        return result;
    }

    private static Issue extractIssueFromNodeList(Node nNode) {
        Issue result = new Issue();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            String name = eElement.getElementsByTagName("name").item(0).getTextContent();
            String id = eElement.getElementsByTagName("id").item(0).getTextContent();
            String image = eElement.getElementsByTagName("image").item(0).getTextContent();
            String issue_number = eElement.getElementsByTagName("issue_number").item(0).getTextContent();
            String description = eElement.getElementsByTagName("description").item(0).getTextContent();
            String cover_date = eElement.getElementsByTagName("cover_date").item(0).getTextContent();
            String store_date = eElement.getElementsByTagName("store_date").item(0).getTextContent();
            String site_detail_url = eElement.getElementsByTagName("site_detail_url").item(0).getTextContent();
            String person_credit = eElement.getElementsByTagName("person_credit").item(0).getTextContent();
            String storyarc_credit = eElement.getElementsByTagName("storyarc_credit").item(0).getTextContent();

            result.setName(name);
            result.setId(id);
            result.setImage_location(description);
            result.setIssue_number(issue_number);
            result.setDescription(description);
            result.setCover_date(cover_date);
            result.setStore_date(store_date);
            result.setSite_detail_url(site_detail_url);
            result.setPerson_credits(person_credit);
            result.setStory_arc_credits(storyarc_credit);
        }
        return result;
    }

    private static Publisher extractPublisherFromNodeList_incomplete(NodeList nodeList) {
        Publisher result = new Publisher();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                String key = element.getTagName();
                String value = element.getNodeValue();

            }

        }
        return result;
    }
}
