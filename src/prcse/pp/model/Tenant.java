package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Create a new Tenant object and allow it to be Observed
 * by the Observer pattern
 */
public class Tenant extends Person implements Serializable {

    /**
     * Variables which describe a tenant,
     */
    protected UserPermission permission;
    private   Property       property;
    private   Room           room;
    private   double         payment_due;

    /**
     * Tenants are able to make payments and send/receive messages,
     * these ArrayLists track all their payments and messages
     */
    private   ArrayList<Message> messages;
    protected ArrayList<Payment> payments;
    private   ArrayList notes;


    /**
     * Constructor for a Tenant (someone who rents a room)
     * @param title users title
     * @param forename users first name
     * @param surname users last name
     * @param email  users email
     * @param number users home/mobile number
     * @param addr_1 users home address line ( NOT RENTED )
     * @param addr_2 users optional second address line ( NOT RENTED )
     * @param postcode users postcode ( NOT RENTED )
     * @param city users home city where this house resides ( NOT RENTED )
     * @param property the property the user is renting
     * @param room the room they are renting in that property (if applicable)
     */
    public Tenant(String title, String forename, String surname, String email, String number,
                String addr_1, String addr_2, String postcode, String city, Property property, Room room)
    {
        super(title, forename, surname, email, number, addr_1, addr_2, postcode, city);

        this.permission  = UserPermission.USER;
        this.property    = property;
        this.room        = room;

        // creates a new message and payments array list
        this.messages = new ArrayList<Message>();
        this.payments = new ArrayList<Payment>();
        this.notes    = new ArrayList();

        // calculates the total payment due from the Tenant
        this.payment_due = this.room.getPrice() * this.room.getContractLength();

        // sets status to occupied if the user has a room.
        this.room.occupied(this);
    }

    /**
     * Empty constructor for a tenant
     */
    public Tenant()
    {
        super();

        this.permission  = UserPermission.USER;
        this.property    = null;
        this.room        = null;
        this.payment_due = 0.00;

        // creates a new message and payments array list
        this.messages = new ArrayList<Message>();
        this.payments = new ArrayList<Payment>();
        this.notes    = new ArrayList();
    }

    /**
     * Adds a payment against the Tenants property by calling the makePayment
     * method in the Property class
     * @param amount the amount being paid
     * @return
     */
    public Boolean makePayment(int amount)
    {
        // Assume the transaction would fail
        Boolean result = false;
        if(property.makePayment(amount, this))
            result = true;

        return result;
    }

    /**
     * Returns the room which the tenant occupies
     */
    public Room getRoom()
    {
        return this.room;
    }

    /**
     * Sets the users new Room
     * @param newRoom the Room which the user now occupies
     * @return True if the room was changed, else false
     */
    public Boolean setRoom(Room newRoom)
    {
        Boolean roomAdded = false;

        // Set the new room
        if(null != newRoom)
        {
            this.room = newRoom;
            roomAdded = true;
        }

        return roomAdded;
    }

    /**
     * Sets the tenants Property
     * @param newProperty the property the tenant occupies
     * @return True if the room was changed else false
     */
    public Boolean setProperty(Property newProperty)
    {
        Boolean propertySet = false;

        // Set the new property
        if(null != newProperty)
        {
            this.property = newProperty;
            propertySet = true;
        }

        return propertySet;
    }

    /**
     *  Returns the users property
     */
    public Property getProperty()
    {
        return this.property;
    }

    /**
     * Sets the payment due for a user
     */
    public Boolean setPaymentDue()
    {
        Boolean paymentSet = false;

        if(paymentSet == false)
        {
            paymentSet = true;
            this.payment_due = this.room.getPrice() * this.room.getContractLength();
        }

        return paymentSet;
    }

    /**
     * Gets the payment due for a user
     * @return the amount of payment a user has due on their contract
     */
    public double getPaymentDue()
    {
        return this.payment_due;
    }

}