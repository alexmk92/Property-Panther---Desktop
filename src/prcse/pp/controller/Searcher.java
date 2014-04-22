package prcse.pp.controller;

import prcse.pp.model.Admin;
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
    Admin    a = new Admin();

    /**
     * Editing flag, default false
     */
    private Boolean editing = false;

    /**
     * Sets the session object (the admin running the session after a successful login)
     * and builds their inbox
     * @param thisAdmin the admin object set at login
     */
    public void setSession(Admin thisAdmin){
        this.a = thisAdmin;
    }

    /**
     * Returns the logged in user
     * @param thisAdmin the admin object set by the session
     */
    public Admin getSessionUser(){
        return a;
    }

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

    /**
     * Sets whether or not we are editing an object
     */
    public void setEditing(Boolean editing) {
        this.editing = editing;
    }

    /**
     * Returns the editing flag
     */
    public Boolean getEditing() {
        return this.editing;
    }
}
