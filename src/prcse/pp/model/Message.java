package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Creates a new Message (either Maintenance Request, Inbox or Alert message)
 * @author PRCSE
 */
public class Message implements ISubject, Serializable {

    private MessageStatus status;
    private MessageType type;
    private User toUser;
    private User fromUser;
    private String message;
    private Property property;
    private transient ArrayList<IObserver> observerList;

    /**
     * Constructor for an inbox message
     * @param toUser the user the message is addressed to
     * @param fromUser the user the message is from
     * @param type the type of message (an Inbox message)
     * @param message the body of the message
     */
    public Message(User toUser, User fromUser, MessageType type, String message) {
        this.toUser   = toUser;
        this.fromUser = fromUser;
        this.type     = type.INBOX;
        this.message  = message;
    }

    /**
     * Alert constructor, allows the landlord to track whether payments have been missed on houses
     * or if a user wants to view a property
     * @param tenant the tenant who the alert is against
     * @param type the type of message ( an alert )
     * @param message the message body
     * @param p the property the alert is against
     */
    public Message(User tenant, MessageType type, String message, Property p) {
        this.fromUser = tenant;
        this.type     = type.ALERT;
        this.message  = message;
        this.property = p;
    }

    /**
     * Maintenance request constructor, allows a request to be made against a new
     * property
     * @param tenant the tenant making the request
     * @param type the type of request
     * @param status the current status of the request (RECEIVED, SEEN, SCHEDULED, IN PROGRESS, COMPLETED)
     * @param message the message the tenant sends
     * @param p the property of which the request is being made against
     */
    public Message(User tenant, MessageType type, MessageStatus status, String message, Property p) {
        this.fromUser     = tenant;
        this.status       = status.RECEIVED;
        this.type         = type.MAINTENANCE;
        this.message      = message;
        this.property     = p;
        this.observerList = new ArrayList<>();
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

    public void setMessage(String request) {
        this.message = request;
    }

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
