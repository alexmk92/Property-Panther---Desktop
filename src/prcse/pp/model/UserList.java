package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A collection of all user objects
 * @author PRCSE
 */
public class UserList implements ISubject, IObserver, Serializable {

    private ArrayList<Tenant> tenantList = new ArrayList();
    private transient ArrayList<IObserver> observers;

    /**
     * Default (empty) constructor, creates a new empty list of users
     */
    public UserList(){
        this.tenantList = new ArrayList<>();
    }


    /**
     * Accessor to add the provided Tenant object to the list of Tenant
     * @param newTenant - The Tenant object to add to the list
     */
    public void addUser(Tenant newTenant){
        if(null != newTenant){
            this.tenantList.add(newTenant);
            this.notifyObservers();
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
            this.notifyObservers();
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
     * Returns a list of tenants who have the same forename
     */
    public ArrayList getTenant(String forename, String surname)
    {
        ArrayList<Tenant> tenantResults = new ArrayList<>();

        String fullname = forename + " " + surname;

        if(null != forename)
        {
            for(int i = 0; i < tenantList.size(); i++)
            {
                String thisName = tenantList.get(i).getForename();
                if(forename.toLowerCase().equals(thisName.toLowerCase()))
                {
                    tenantResults.add(tenantList.get(i));
                }
            }
        }

        return tenantResults;
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

    /**
     * Registers a new Observer
     * @param o - The observer we are registering
     * @return   true if the Observer is registered
     */
    @Override
    public Boolean registerObserver(IObserver o)
    {
        Boolean blnAdded = false;                   //Assume this method will fail
        //Validate that observer exists
        if (o != null)
        {
            //If observer list not initialised create it
            if (this.observers == null)
            {
                this.observers = new ArrayList<>();
            }
            //Add the observer to the list of registered observers
            if (!this.observers.contains(o))
            {
                blnAdded = this.observers.add(o);
            }
            //Make sure all Users are observed
            if (blnAdded)
            {
                for (Tenant currTenant : this.tenantList)
                {
                    currTenant.registerObserver(this);
                }
            }
        }
        //Return the result
        return blnAdded;
    }

    @Override
    public Boolean removeObserver(IObserver o)
    {
        Boolean blnRemoved = false;
        //Validate we have something to remove
        if (o != null)
        {
            if (this.observers != null && this.observers.size() > 0)
            {
                blnRemoved = this.observers.remove(o);
            }
        }
        return blnRemoved;
    }

    @Override
    public void notifyObservers()
    {
        //Ensure we have a valid list of observers
        if (this.observers != null && this.observers.size() > 0)
        {
            //Start foreach loop
            for (IObserver currentObserver : this.observers)
            {
                //Call update on this observer
                currentObserver.update();
            }
        }
    }

    @Override
    public void update()
    {
        this.notifyObservers();
    }

    /**
     * Gets all observers within the current observer arraylist
     */
    public ArrayList<IObserver> getObservers()
    {
        ArrayList<IObserver> arlResult = new ArrayList<>();
        for (IObserver currObserver : observers)
        {
            arlResult.add(currObserver);
        }
        return arlResult;
    }
}