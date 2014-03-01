package prcse.pp.controller;

import prcse.pp.model.Tenant;
import prcse.pp.model.UserList;

import java.util.ArrayList;

/**
 * Creates a search object which allows data to persist through
 * different forms.   This is required as all of the forms are
 * loaded into a hashmap when the program starts, therefore new
 * form instantiations are never created, therefore this object
 * is needed to perform data persistence methods between each
 * form.
 */
public class Searcher {

    /**
     * Variables which can be searched on to persist through forms
     */
    Tenant   t = new Tenant();
    UserList u = new UserList();

    /**
     * Sets a tenant object to allow for data persistence between
     * forms.
     * @param thisTenant the tenant we want to manipulate
     */
    public void setTenant(Tenant thisTenant)
    {
        this.t = thisTenant;
    }

    /**
     * Gets the current Tenant object which has been set in the
     * Searcher class
     * @return Tenant object if found, else return null
     */
    public Tenant getTenant()
    {
        Tenant thisTenant = null;

        if(null != this.t)
        {
            thisTenant = t;
        }

        return thisTenant;
    }

    /**
     * Creates a new UserList object which can be
     * used to search for users in the system, this differs
     * from the UserList initialised from the database on the
     * main menu (ScreensFramework)
     * @return a UserList object, containing all searched users.
     */
    public void setSearchedUsers(UserList thisUserList)
    {
         this.u = thisUserList;
    }


    /**
     * Returns the current set UserList object
     * @return null if the object is not set, else return the list
     */
    public UserList getSearchedUsers()
    {
        UserList thisUserList = null;

        if(null != this.u)
        {
            thisUserList = u;
        }

        return thisUserList;
    }


}
