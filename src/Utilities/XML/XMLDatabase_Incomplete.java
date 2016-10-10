package Utilities.XML;

import Model.GenericObject;
import Model.LongBox;
import Utilities.XML.FileName;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static void addObject(GenericObject obj) {
        
    }
    
    public static void removeObject(GenericObject obj){
        
    }
    
    public static void modifyObject(GenericObject obj){
        
    }

    public static void main(String[] args) {
        XMLDatabase_Incomplete sampleDatabase = new XMLDatabase_Incomplete(FileName.FileSamplePublishers, FileName.FileSamplePersons,FileName.FileSampleVolumes
                ,FileName.FileSampleIssues,FileName.FileSampleStoryArcs);
        System.out.println(sampleDatabase.toString());
    }
}
