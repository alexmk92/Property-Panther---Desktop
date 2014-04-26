package prcse.pp.model;

import prcse.pp.controller.ScreensFramework;
import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;
import prcse.pp.model.MessageType;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Creates a new Message (either Maintenance Request, Inbox or Alert message)
 * @author PRCSE
 */
public class Message implements ISubject, Serializable {

    protected MessageStatus status;
    protected MessageType   type;
    private   Tenant        toTenant;
    private   Tenant        fromTenant;
    private   String        message;
    private   Date          date;
    private   int           id;
    private   int           seen;
    private   int           request_id;

    private transient ArrayList<IObserver> observerList = null;

    /**
     * Empty constructor for unset messages
     */
    public Message()
    {
        this.toTenant   = null;
        this.fromTenant = null;
        this.type       = null;
        this.status     = null;
        this.message    = "";
        this.date       = getTodaysDate();
        this.id         = 0;
        this.seen       = 0;
    }

    /**
     * Constructor for a message which only has a message
     */
    public Message(String message, Date theDate, int id)
    {
        this.message = message;
        if(theDate == null){
            this.date    = getTodaysDate();
        } else {
            this.date = theDate;
        }
        this.id         = id;
    }

    /**
     * Constructor for a message with one recipient and no sender
     * @param thisTenant the tenant whom the message is directed to
     * @param message the message supplied
     */
    public Message(Tenant thisTenant, String message, Date theDate, int id, String req_status, String req_id)
    {
        this.toTenant    = thisTenant;
        this.message     = message;
        this.status      = setStatus(req_status);
        if(theDate == null || theDate.equals("")){
            this.date    = getTodaysDate();
        } else {
            this.date = theDate;
        }
        this.id         = id;
    }

    /**
     * Constructor for a message with a sender and recipient
     * @param toTenant recipient
     * @param fromTenant sender
     * @param message message supplied
     */
    public Message(Tenant toTenant, Tenant fromTenant, String message, Date theDate, int id)
    {
        this.toTenant   = toTenant;
        this.fromTenant = fromTenant;
        this.message    = message;
        if(theDate == null || theDate.equals("")){
            this.date    = getTodaysDate();
        } else {
            this.date = theDate;
        }
        this.id         = id;
    }

    /**
     * Constructor for a generic inbox/sent message when INSERTING into the system
     * @param msgId - the ID retrieved from the search
     * @param toTenant - the id of the tenant retrieved
     * @oaram fromTenant - the id of the tenant retrieved, else 0 (set on build statement to void a null pointer exception)
     * @param type - the type of message (ALERT, MAINTENANCE, INBOX)
     * @param msg  - the body of the message
     * @param theDate - the datethe message was sent (for sorting)
     * @param read - Has the message been read of not
     */
    public Message(int msgId, int toTenant, int fromTenant, String type, String msg, Date theDate, int read, int request_id)
    {
        this.id          = msgId;
        this.toTenant    = ScreensFramework.tenants.getUserById(toTenant);
        this.fromTenant  = ScreensFramework.tenants.getUserById(fromTenant);
        this.type        = typeToString(type);
        this.message     = msg;
        this.seen        = read;
        this.request_id  = request_id;


        if(theDate == null || theDate.equals("")){
            this.date    = getTodaysDate();
        } else {
            this.date = theDate;
        }
    }

    // Setters and getters
    public MessageStatus getStatus() {
        return status;
    }

    /**
     * Update the status of the message to seen
     */
    public MessageStatus setStatus(String status)
    {
        MessageStatus thisStatus = MessageStatus.RECEIVED;

        // Get the correct status
        switch(status){
            case "RECEIVED":
                thisStatus = MessageStatus.RECEIVED;
                break;
            case "IN_PROGRESS":
                thisStatus = MessageStatus.IN_PROGRESS;
                break;
            case "SCHEDULED":
                thisStatus = MessageStatus.SCHEDULED;
                break;
            case "SEEN":
                thisStatus = MessageStatus.SEEN;
                break;
            case "COMPLETED":
                thisStatus = MessageStatus.COMPLETED;
                break;
        }

        return thisStatus;
    }

    /**
     * Get the status as a string
     * @param status - the MessageStatus we set
     */
    public String getStatusAsString(MessageStatus status) {

        String thisStatus = "RECEIVED";

        // Get the correct status
        switch(status){
            case RECEIVED:
                thisStatus = "RECEIVED";
                break;
            case IN_PROGRESS:
                thisStatus = "IN PROGRESS";
                break;
            case SCHEDULED:
                thisStatus = "SCHEDULED";
                break;
            case SEEN:
                thisStatus = "SEEN";
                break;
            case COMPLETED:
                thisStatus = "COMPLETED";
                break;
        }

        return thisStatus;
    }

    public int getRequestId() {
        return this.request_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public Date getTodaysDate() {

        // Set the date on the label
        Calendar c = Calendar.getInstance();

        Date dateOut = c.getTime();

        return dateOut;
    }

    public String getSender() {
        String name = "";
        try {
            name = fromTenant.getName();
        } catch (Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }
        return name;
    }

    public String getSenderEmail() {
        String email = "";
        try {
            email = fromTenant.getEmail();
        } catch (Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }
        return email;
    }

    public String getRecipient() {
        String name = "";
        try {
            name = toTenant.getEmail();
        } catch (Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }
        return name;
    }

    public String getRecipientEmail() {
        String email = "";
        try {
            email = toTenant.getEmail();
        } catch (Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }
        return email;
    }

    public String getDateAsString(Date thisDate) {

        // Set the date on the label
        SimpleDateFormat today = new SimpleDateFormat("dd MMM yyyy");
        String dateOut = today.format(thisDate.getTime());

        return dateOut;
    }

    public Date getDate() {
        return this.date;
    }

    public int getId() {

        String thisQuery = "";

        // Get the message from the database (assumes that there wont be duplicate messages
        if(this.id == 0) {
            switch(this.type) {
                case NOTE:
                    thisQuery = "SELECT note_id FROM notes WHERE note_body = '" + this.getMessage() + "'";
                break;
                case MAINTENANCE:
                    thisQuery = "SELECT request_id FROM requests WHERE request_details = '" + this.getMessage() + "'";
                break;
                case ALERT:
                    thisQuery = "SELECT message_id FROM messages WHERE message_type='ALERT' AND message_body = '" + this.getMessage() + "'";
                break;
                case INBOX:
                    thisQuery = "SELECT message_id FROM messages WHERE message_type='INBOX' AND message_body = '" + this.getMessage() + "'";
                break;
                default:
                    ScreensFramework.logError.writeToFile("Error: Writing to the wrong file when retrieving id.");
                break;
            }

            this.id = ScreensFramework.db.selectInt(thisQuery, this.type);
        }

        return this.id;
    }

    /**
     * Returns the current type
     */
    public String getType() {
        return this.type.toString();
    }

    /**
     * Gets the type
     * @param type - the type String retrieved from the database
     * @return t - The formatted type
     */
    public MessageType typeToString(String type) {

        MessageType t = null;

        // Get the message from the database (assumes that there wont be duplicate messages
        if(type.length() > 0) {
            switch(type) {
                case "NOTE":
                    t = MessageType.NOTE;
                    break;
                case "MAINTENANCE":
                    t = MessageType.MAINTENANCE;
                    break;
                case "ALERT":
                    t = MessageType.ALERT;
                    break;
                case "INBOX":
                    t = MessageType.INBOX;
                    break;
                default:
                    t = MessageType.INBOX;
                    break;
            }
        }

        return t;
    }

    /**
     * Returns the read count of this object
     */
    public int getRead() {
        return this.seen;
    }

    /**
     * Sets seen to 1
     */
    public void setSeenTrue() {
        this.seen = 1;
    }

    public void setId(int idIn) {
        this.id = idIn;
    }

    public void setDate(Date dateIn) {
        this.date = dateIn;
    }

    public String convertDate(Date date) {

        // Set the date on the label
        Calendar c = Calendar.getInstance();

        SimpleDateFormat today = new SimpleDateFormat("dd MMM yyyy");
        String dateOut = today.format(date);

        return dateOut;
    }



    // Observer Stuff
    @Override
    public Boolean registerObserver(IObserver o) {
        Boolean result = false;
        if (null != o) {
            if (!this.observerList.contains(o)) {
                result = this.observerList.add(o);
            }
        }
        return result;
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean result = false;
        if(null != o){
            result = this.observerList.remove(o);
        }
        return result;
    }

    @Override
    public void notifyObservers() {
        if(null != this.observerList && 0 < this.observerList.size()){
            for(IObserver currObserver : this.observerList){
                currObserver.update();
            }
        }
    }


}
