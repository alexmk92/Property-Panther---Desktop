package prcse.pp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a new User with Admin privileges
 */
public class Admin extends Person implements Serializable {

    /**
     * Variables which describe an admin
     */
    private UserPermission permission;
    private int userId;

    // Admin arrays
    private ArrayList<Alert> alerts;
    private ArrayList<Inbox> inbox;

    public Admin()
    {
        super();
        this.permission = UserPermission.ADMIN;

        this.alerts     = new ArrayList<Alert>();
        this.inbox      = new ArrayList<Inbox>();

    }

    /**
     * Admin with a forename, surname, password and email
     */
    public Admin(int userId, String forename, String surname, String email)
    {
        super(forename, surname);
        this.userId     = userId;
        this.email      = email;
        this.permission = UserPermission.ADMIN;

        this.alerts     = new ArrayList<Alert>();
        this.inbox      = new ArrayList<Inbox>();
    }

    /**
     * Allows an admin to set a given users permissions
     * @param newPermission the permission level we want to set
     * @param thisTenant the user who's privileges we are changing
     */
    public void grantPermission(UserPermission newPermission, Tenant thisTenant){
        if(this.permission == UserPermission.ADMIN)
            thisTenant.permission = newPermission;
        else
            System.out.println("Sorry, only admins can escalate permissions.");
    }

    /**
     * Revokes the permissions of a user and sets them back to
     * default permissions
     * @param thisTenant the user who is having their permissions amended
     */
    public void revokePermissions(Tenant thisTenant){
        if(this.permission == UserPermission.ADMIN)
            thisTenant.permission = UserPermission.USER;
        else
            System.out.println("Sorry, only admins can revoke permissions.");
    }


}
