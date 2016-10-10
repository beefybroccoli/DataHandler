/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.*;
import Model.GenericObject;
import Model.LongBox;
import Utilities.FileName;
import Utilities.SAXGenericHandler;

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
}
