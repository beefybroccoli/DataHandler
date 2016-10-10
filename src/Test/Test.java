/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.*;
import Model.GenericObject;
import Model.LongBox;
import Utilities.XML.FileName;
import Utilities.XML.QueryString;
import static Utilities.XML.QueryString.expression;
import Utilities.XML.SAXGenericHandler;
import Utilities.XML.XMLQuery;
import Utilities.XML.XMLDatabase_Incomplete;

public class Test {

    public static void main(String[] args) throws Exception {

        /*
        return a LongBox<GenericObject> that contains object from reading file.
        input : file name
        output : LongBox<GenericObject> 
         */
        test_SAX_Generic_Handler();

        /*
        GenericObject is the super class of all class in Model package, except LongBox object
        LongBox<GenericObject> can be used to contain any objects in the Model package.
         */
        test_LongBex_Object();

        /*
        input: query string, file name
        output : LongBox<GenericObject>
        search through file for a specific query
         */
        runQueriesFromFile();
        
        
        /*
        input: String publisherFile, String personFile, String VolumeFile, String issueFile, String storyarcFile
        output: XMLDatabase_Incomplete object
        */
        test_XML_database();
    }

    public static void runQueriesFromFile() throws Exception {

        LongBox<GenericObject> box = new LongBox<GenericObject>();
        box = XMLQuery.query(QueryString.AllPublisher, FileName.FileSamplePublishers);

        box = XMLQuery.query(QueryString.AllPerson, FileName.FileSamplePersons);

        box = XMLQuery.query(QueryString.AllIssue, FileName.FileSampleIssues);

        box = XMLQuery.query(QueryString.AllVolume, FileName.FileSampleVolumes);

        box = XMLQuery.query(QueryString.AllStoryArc, FileName.FileSampleStoryArcs);

        String expression;

        expression = QueryString.expression("publisher", "name", "marvels");
        box = XMLQuery.query(expression, FileName.FileSamplePublishers);

        expression = QueryString.expression("person", "name", "author marvel");
        box = XMLQuery.query(expression, FileName.FileSamplePersons);

        expression = QueryString.expression("issue", "name", "title of issue 5");
        box = XMLQuery.query(expression, FileName.FileSampleIssues);

        expression = QueryString.expression("volume", "name", "volume one");
        box = XMLQuery.query(expression, FileName.FileSampleVolumes);

        expression = QueryString.expression("storyarc", "name", "marvel storyarc 1");
        box = XMLQuery.query(expression, FileName.FileSampleStoryArcs);

    }

    public static void test_SAX_Generic_Handler() throws Exception {
        System.out.println("----------------test_SAX_Generic_Handler() test----------");
        String[] inputName = {
            FileName.FileSamplePublishers, FileName.FileSampleIssues, FileName.FileSamplePersons, FileName.FileSampleStoryArcs, FileName.FileSampleVolumes
        };

        for (int i = 0; i < inputName.length; i++) {
            LongBox<GenericObject> box = SAXGenericHandler.readXML(inputName[i]);
            System.out.println("box size is " + box.size());
        }
    }

    public static void test_LongBex_Object() {
        System.out.println("----------------test_LongBex_Object() test----------");
        LongBox<GenericObject> box = new LongBox<GenericObject>();

        box.add((GenericObject) new Publisher());
        box.add((GenericObject) new Issue());
        box.add((GenericObject) new Person());
        box.add((GenericObject) new StoryArc());
        box.add((GenericObject) new Volume());

        System.out.println("the box size is " + box.size());

    }

    public static void test_XML_database() {
        XMLDatabase_Incomplete sampleDatabase = new XMLDatabase_Incomplete(FileName.FileSamplePublishers, FileName.FileSamplePersons, FileName.FileSampleVolumes, FileName.FileSampleIssues, FileName.FileSampleStoryArcs);
        System.out.println(sampleDatabase.toString());
    }
}
