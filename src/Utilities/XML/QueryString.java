/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities.XML;

public class QueryString {

    //select all elements whose key and value paired are specified
    //public static final String PublisherByName = "//publisher[name='marvels']";
    
    //select the first publisher
    //public static final String expression = "/publishers/publisher[1]";
    
    //select all elements that are children of publishers 
    public static final String AllPublisher = "//publisher";

    //select all elements that are children of publishers
    public static final String AllIssue = "//issue";

    //select all elements that are children of publishers
    public static final String AllVolume = "//volume";

    //select all elements that are children of publishers
    public static final String AllStoryArc = "//storyarc";

    //select all elements that are children of publishers
    public static final String AllPerson = "//person";

    //select all elements whose key and value paired are specified
    //public static final String All  = "/publishers/publisher[name='marvels']";
    //select all elements whose key and value paired are specified
    //public static final String expression  = "/publishers/publisher[name=*]";
    
    public static String expression(String objectType, String inputVariableName
            , String inputValue) {
        //"publisher[name='marvels']"
        return "//" + objectType + "["  + inputVariableName 
                + "='" +  inputValue + "']";
    }
    
    public static void main (String[] args) {
        System.out.println(expression("publisher", "name", "marvels"));
    }
}
