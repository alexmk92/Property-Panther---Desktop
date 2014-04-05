package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Creates a new Message (either Maintenance Request, Inbox or Alert message)
 * @author PRCSE
 */
public class Message implements ISubject, Serializable {

    protected MessageStatus status;
    protected MessageType type;
    private   Tenant toTenant;
    private   Tenant fromTenant;
    private   String message;
    private   Boolean viewed;
    private   String date;

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
    }

    /**
     * Constructor for a message which only has a message
     */
    public Message(String message, String theDate)
    {
        this.message = message;
        if(theDate.length() < 1 || theDate.equals("")){
            this.date    = getTodaysDate();
        } else {
            this.date = theDate;
        }
    }

    /**
     * Constructor for a message with one recipient and no sender
     * @param thisTenant the tenant whom the message is directed to
     * @param message the message supplied
     */
    public Message(Tenant thisTenant, String message, String theDate)
    {
        this.toTenant = thisTenant;
        this.message  = message;
        if(theDate.length() < 1 || theDate.equals("")){
            this.date    = getTodaysDate();
        } else {
            this.date = theDate;
        }
    }

    /**
     * Constructor for a message with a sender and recipient
     * @param toTenant recipient
     * @param fromTenant sender
     * @param message message supplied
     */
    public Message(Tenant toTenant, Tenant fromTenant, String message, String theDate)
    {
        this.toTenant   = toTenant;
        this.fromTenant = fromTenant;
        this.message    = message;
        if(theDate.length() < 1 || theDate.equals("")){
            this.date    = getTodaysDate();
        } else {
            this.date = theDate;
        }
    }

    // Setters and getters
    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getTodaysDate() {

        // Set the date on the label
        Calendar c = Calendar.getInstance();

        SimpleDateFormat today = new SimpleDateFormat("dd MMM yyyy");
        String dateOut = today.format(c.getTime());

        return dateOut;
    }

    public String getDate() {
        return this.date;
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
