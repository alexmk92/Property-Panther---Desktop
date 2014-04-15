package prcse.pp.model;

import prcse.pp.controller.ScreensFramework;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private ArrayList<Message> inbox;
    private ArrayList<Message> sentBox;

    public Admin()
    {
        super();
        this.permission = UserPermission.ADMIN;

        this.inbox      = new ArrayList<Message>();
        this.sentBox    = new ArrayList<Message>();
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
        this.userId     = userId;

        this.inbox      = new ArrayList<Message>();
        this.sentBox    = new ArrayList<Message>();
    }


    /**
     * Returns the inbox array
     */
    public ArrayList<Message> getInbox() {
        return this.inbox;
    }

    /**
     * Returns the sent box array
     */
    public ArrayList<Message> getSentBox() {
        return this.sentBox;
    }


    /**
     * Gets the size of the sent array
     * @return size - the size of the array
     */
    public int getSentSize() {
        return this.inbox.size();
    }

    /**
     * Gets the size of the inbox array
     * @return size - the size of the array
     */
    public int getInboxSize() {
        return this.inbox.size();
    }

    /**
     * Builds the inbox for the admin user
     * @param amount - the amount of messages we want to load by default
     */
    public void buildInbox(int amount, String type) {

        // Empty the current array
        this.inbox.clear();

        try {
            // The query to be built
            String query = "";

            // Build the query string
            switch(type) {
                case "ALL":
                    query = "SELECT * FROM messages WHERE message_to = " + this.getId() + " OR message_to IS NULL ORDER BY message_sent ASC FETCH FIRST " + amount + " ROWS ONLY";
                    break;
                case "ALERT":
                    query = "SELECT * FROM messages WHERE message_to IS NULL AND message_type='ALERT' ORDER BY message_sent ASC FETCH FIRST " + amount + " ROWS ONLY";
                    break;
                case "INBOX":
                    query = "SELECT * FROM messages WHERE message_to = " + this.getId() + " AND message_type='INBOX' ORDER BY message_sent ASC FETCH FIRST " + amount + " ROWS ONLY";
                    break;
                case "MAINTENANCE":
                    query = "SELECT * FROM messages WHERE message_to = " + this.getId() + " AND message_type='MAINTENANCE' " +
                            "OR message_to IS NULL AND message_type='MAINTENANCE' ORDER BY message_sent ASC FETCH FIRST " + amount + " ROWS ONLY";
                    break;
            }

            // Run the query and collect the results
            ResultSet   res = ScreensFramework.db.query(query);

            // Initialise to 0
            int fromTenant = 0;
            while(res.next()){


                // Build the message
                Message m = new Message(res.getInt("message_id"), res.getInt("message_to"), res.getInt("message_from"), res.getString("message_type"),
                                        res.getString("message_body"), res.getDate("message_sent"), res.getInt("message_read"));

                // Add the message to the inbox
                this.inbox.add(m);
            }
        } catch(Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }
    }

    /**
     * Builds the sent box for the admin user
     * @param amount - the amount of messages we want to load by default
     */
    public void buildSent(int amount) {

        // Empty the current sent box
        this.sentBox.clear();

        try {
            // Query the database
            String    query = "SELECT * FROM messages WHERE message_from = " + this.getId() + " ORDER BY message_sent ASC FETCH FIRST " + amount + " ROWS ONLY";
            ResultSet   res = ScreensFramework.db.query(query);

            while(res.next()){
                // Build the message
                Message m = new Message(res.getInt("message_id"), res.getInt("message_to"), res.getInt("message_from"), res.getString("message_type"),
                        res.getString("message_body"), res.getDate("message_sent"), res.getInt("message_read"));

                // Add the message to the sent box
                this.sentBox.add(m);
            }
        } catch(Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }
    }

    /**
     * Gets the message at the given index
     * @param box   - either "INBOX" or "SENT" to search
     * @param index - the index we wish to access
     * @return msg  - the message found after processing, else null
     */
    public Message getMessageAt(String box, int index) {
        Message msg = null;

        switch(box) {
            case "INBOX":
                if(this.inbox.get(index) != null){
                    msg = this.inbox.get(index);
                }
                break;
            case "SENT":
                if(this.sentBox.get(index) != null){
                    msg = this.sentBox.get(index);
                }
                break;
        }
        return  msg;
    }

    /**
     * Gets the amount of read messages in the box
     * @return amount - the Amount of read messages
     */
    public int totalRead() {
        int read = 0;

        // Increment read by 1 for each positive value
        for(int i = 0; i < this.inbox.size(); i++){
            Message m = this.inbox.get(i);
            if(m.getRead() == 1){
                read++;
            }
        }

        return read;
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
            ScreensFramework.logError.writeToFile("Sorry, only admins can escalate permissions.");
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
            ScreensFramework.logError.writeToFile("Sorry, only admins can revoke permissions.");
    }

    /**
     * Returns the userid of the admin
     * @return userId
     */
    public int getId() {
        return this.userId;
    }

    /**
     * returns the permission of this admin
     * @return
     */
    public UserPermission getPermission(){
        return permission;
    }

}
