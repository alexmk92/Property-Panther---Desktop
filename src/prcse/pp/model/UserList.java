package prcse.pp.model;

import prcse.pp.controller.ScreensFramework;
import prcse.pp.controller.Searcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of all user objects
 * @author PRCSE
 */
public class UserList {

    private ArrayList<Tenant> tenantList;

    /**
     * Default (empty) constructor, creates a new empty list of users
     */
    public UserList(){
        this.tenantList = new ArrayList<Tenant>();
    }


    /**
     * Accessor to add the provided Tenant object to the list of Tenant
     * @param newTenant - The Tenant object to add to the list
     */
    public void addUser(Tenant newTenant){
        if(null != newTenant){
            this.tenantList.add(newTenant);
        }
    }

    /**
     * Accessor to remove a Tenant from the Tenant list. The index parameter
     * identifies the Tenant to be removed.
     * @param index - A zero based index value specifying the Tenant to
     * remove from the list
     * @return The Tenant object removed from the list OR NULL if the index value
     * was invalid.
     */
    public Tenant removerUserAt(int index){
        Tenant result = null;
        if(index >= 0 && index < this.tenantList.size()){
            result = this.tenantList.remove(index);
        }
        return result;
    }

    /**
     * Accessor to retrieve the Tenant object specified by the index parameter
     * @param index - A zero based index value specifying the Object required from the list
     * @return - The Tenant at the specified index or NULL if no user is found
     */
    public Tenant getUserAt(int index){
        Tenant result = null;
        if(index >= 0 && index < this.tenantList.size()){
            result = this.tenantList.get(index);
        }
        return result;
    }

    /**
     * Returns the user based on their ID
     * @return the user with the requested id
     */
    public Tenant getUserById(int userId){
        Tenant result = null;

        // Check to see we have the right user
        if(userId >= 0){
            for(int i = 0; i < tenantList.size(); i++){
                if(this.getUserAt(i).getUserId() == userId){
                    result = this.getUserAt(i);
                }
            }
        }

        return result;
    }

    /**
     * This method creates a string array containing all the names of the current
     * Tenant Objects in the format "forename surname" -- MAY CHANGE THIS LATER
     * @return A String array of Tenant names.
     */
    public String[] getAllUsers(int index){
        String[] result = new String[this.tenantList.size()];
        for(int i = 0; i < this.tenantList.size(); i++){
            Tenant record = this.tenantList.get(i);
            if(null != record){
                result[i] = record.getName();
            }
        }
        return result;
    }

    /**
     * Returns a list of tenants who have the same name
     */
    public UserList getTenant(String forename, String surname)
    {
        UserList tenantResults = new UserList();
        String fullname = forename + " " + surname;
        Searcher search = ScreensFramework.searchObj;

        if(fullname != null)
        {
            for(int i = 0; i < tenantList.size(); i++)
            {
                if(fullname.length() > 0)
                {
                    String thisName = tenantList.get(i).getName();
                    String thisForename = tenantList.get(i).getForename();
                    if(forename.toLowerCase().equals(thisForename.toLowerCase()))
                    {
                        tenantResults.addUser(tenantList.get(i));
                    }
                }
                else if(forename.length() > 0)
                {

                }
            }
        }
        search.setSearchedUsers(tenantResults);
        return tenantResults;
    }

    /**
     * Gets the tenant by name
     * @return the found tenant else empty tenant
     */
    public Tenant getTenantByName(String forename, String surname){
        Tenant t = new Tenant();

        for(int i = 0; i < this.size(); i++){
            Tenant currTenant = getUserAt(i);

            // Check we have a match
            if(currTenant.getName().toUpperCase().equals(forename + " " + surname)){
                t = currTenant;
                return t;
            }
        }

        return t;
    }

    /**
     * Gets the tenant by email
     * @return the found tenant else empty tenant
     */
    public Tenant getTenantByEmail(String email){
        Tenant t = new Tenant();

        for(int i = 0; i < this.size(); i++){
            Tenant currTenant = getUserAt(i);

            // Check we have a match
            if(currTenant.getEmail().equals(email)){
                t = currTenant;
                return t;
            }
        }

        return t;
    }

    /**
     * Accessor to retrieve the size of the Tenant list
     * @return - int being the number of Users currently on the user list
     */
    public int size(){
        return this.tenantList.size();
    }

    /**
     * Format the output string of UserList.toString();
     * @return newly formatted value of .toString()
     */
    @Override
    public String toString() {
        return "UserList{" + "list=" + tenantList + '}';
    }

}