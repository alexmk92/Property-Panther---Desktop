package prcse.pp.model;

import prcse.pp.controller.ScreensFramework;
import prcse.pp.controller.Searcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of all property objects
 * @author PRCSE
 */
public class PropertyList {

    private ArrayList<Property> propertyList;

    /**
     * Default (empty) constructor, creates a new empty list of properties
     */
    public PropertyList(){
        this.propertyList = new ArrayList<Property>();
    }


    /**
     * Accessor to add the provided Property object to the list of Proprties
     * @param newProperty - The Property object to add to the list
     */
    public void addProperty(Property newProperty){
        if(null != newProperty){
            this.propertyList.add(newProperty);
        }
    }

    /**
     * Accessor to remove a Property from the Property list. The index parameter
     * identifies the Property to be removed.
     * @param index - A zero based index value specifying the Property to
     * remove from the list
     * @return The Property object removed from the list OR NULL if the index value
     * was invalid.
     */
    public Property removePropertyAt(int index){
        Property result = null;
        if(index >= 0 && index < this.propertyList.size()){
            result = this.propertyList.remove(index);
        }
        return result;
    }

    /**
     * Accessor to retrieve the Property object specified by the index parameter
     * @param index - A zero based index value specifying the Object required from the list
     * @return - The Property at the specified index or NULL if no user is found
     */
    public Property getPropertyAt(int index){
        Property result = null;
        if(index >= 0 && index < this.propertyList.size()){
            result = this.propertyList.get(index);
        }
        return result;
    }

    /**
     * This method creates a string array containing all the names of the current
     * Property Objects
     * @return A String array of Property names.
     */
    public ArrayList<Property> getAllProperties(){
        return this.propertyList;
    }

    /**
     * Accessor to retrieve the size of the Property list
     * @return - int being the number of Properties currently on the user list
     */
    public int size(){
        return this.propertyList.size();
    }

    /**
     * Format the output string of PropertyList.toString();
     * @return newly formatted value of .toString()
     */
    @Override
    public String toString() {
        return "UserList{" + "list=" + propertyList + '}';
    }

}