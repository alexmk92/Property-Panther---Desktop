package prcse.pp.model;

import javafx.application.Platform;
import prcse.pp.controller.ScreensFramework;
import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Create a new Tenant object and allow it to be Observed
 * by the Observer pattern
 */
public class Tenant extends Person implements Serializable {

    /**
     * Variables which describe a tenant,
     */
    private   int            user_id;
    protected UserPermission permission;
    private   Property       property;
    private   Room           room;
    private   double         payment_due;

    /**
     * Tenants are able to make payments and send/receive messages,
     * these ArrayLists track all their payments and messages
     */
    private   ArrayList<Request>  requests;
    private   ArrayList<Note>     notes;
    protected ArrayList<Payment>  payments;


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
    public Tenant(int userId, String title, String forename, String surname, String email, String number,
                String addr_1, String addr_2, String postcode, String city, Property property, Room room)
    {
        super(title, forename, surname, email, number, addr_1, addr_2, postcode, city);

        this.user_id     = userId;
        this.property    = property;
        this.room        = room;

        // creates a new message and payments array list
        this.notes    = new ArrayList<Note>();
        this.requests = new ArrayList<Request>();
        this.payments = new ArrayList<Payment>();

        // calculates the total payment due from the Tenant
        if(property != null && room != null) {
            this.payment_due = this.room.getPrice() * this.room.getContractLength();

            // sets status to occupied if the user has a room.
            this.room.occupied(this);
        }
        // sets the permission to USER
        this.permission  = UserPermission.USER;
    }

    /**
     * Empty constructor for a tenant
     */
    public Tenant()
    {
        super();

        this.user_id     = 0;
        this.permission  = UserPermission.USER;
        this.property    = null;
        this.room        = null;
        this.payment_due = 0.00;

        // creates a new message and payments array list
        this.requests = new ArrayList<Request>();
        this.payments = new ArrayList<Payment>();
        this.notes    = new ArrayList();
    }

    /**
     * Returns the full address as a string
     */
    public String getAddress() {
        String address = "";

        // Check the format
        if(addr_line_2 != null){
            address = getAddr_line_1() + ",\n" + getAddr_line_2() + ",\n" + getPostcode();
        } else {
            address = getAddr_line_1() + ",\n" + getPostcode();
        }

        return address;
    }

    /**
     * Returns the size of the payments array
     */
    public int numOfPayments() {
        return this.payments.size();
    }

    /**
     * Returns the Payment at the given index
     * @param index - the given index we want to locate the payment
     * @return p - the Payment object found at the index, else null
     */
    public Payment getPaymentAt(int index) {

        Payment p = null;

        // Check we have found the correct payment
        if(payments.get(index) != null) {
            p = payments.get(index);
        }

        return p;
    }

    /**
     * Removes all the payments from the payment array
     * @param delete - True if deleting from the Database and array,
     *                 False if just emptying list for re-populate
     * @return True if completed else false
     */
    public Boolean removeAllPayments(Boolean delete) {

        Boolean removed = false;

        if(delete == true){
            String thisQuery =  "DELETE FROM payments WHERE user_id = " + this.getUserId() + "";
            ScreensFramework.db.query(thisQuery);
        }

        // reset to the empty array
        this.payments.clear();

        System.out.println(payments.size());

        removed = true;

        return removed;
    }


    /**
     * Returns the size of the notes array the user has
     */
    public int numOfNotes() {
        return this.notes.size();
    }

    /**
     * Gets the note at the given index
     * @return the note at the given index
     */
    public Note getNoteAt(int index) {

        Note n = null;

        if(this.notes.get(index) != null){
            n = this.notes.get(index);
        }
        return n;
    }

    /**
     * Adds a new note to the note array
     * @param newNote the note object to add
     */
    public Boolean addNote(Note newNote, Boolean insert) {
        Boolean added = false;

        if(newNote != null) {

            // Construct the new note and reference to this user
            String msg  = newNote.getMessage();
            String date = newNote.getDateAsString(newNote.getDate());
            int    id   = this.getUserId();

            this.notes.add(newNote);

            // Determines whether we want to just add to the list (on loading) or if
            // we want to insert into the db aswell
            if(insert == true){
                // Construct the query parameter
                String thisQuery = "INSERT INTO notes VALUES('', " + id + ", '" + msg + "', '" + date + "')";

                // Run the query
                ScreensFramework.db.query(thisQuery);
            }

            added = true;
        }

        return added;
    }

    /**
     * Removes the note at the given index
     * @return true if the note was removed else false
     */
    public Boolean removeNoteAt(int index, Boolean queryDb) {
        Boolean removed = false;

        // Check we are in the correct bounds
        if(index >= 0 && index <= notes.size()){

            // Get the note at the index
            Note n = getNoteAt(index);
            System.out.println(n.getMessage());

            // Check if we need to run the query
            if(queryDb == true) {
                // Construct the query parameter
                String thisQuery = "DELETE FROM notes WHERE note_id = " + n.getId() + " AND user_id = " + this.getUserId() + "";

                // Run the query
                ScreensFramework.db.query(thisQuery);
            }

            // Remove the note from the index
            this.notes.remove(index);

            removed = true;
        }

        return removed;
    }

    /**
     * Removes all notes in the array
     * @return true if the notes were removed else false
     */
    public Boolean removeAllNotes() {

        Boolean removed = false;

            String thisQuery =  "DELETE FROM notes WHERE user_id = " + this.getUserId() + "";
            ScreensFramework.db.query(thisQuery);

             // We dont need to query the db as we remove all records above
            for(int i = 0; i < notes.size(); i++) {
                removeNoteAt(i, false);
            }

            removed = true;

        return removed;
    }

    /**
     * Adds a new request to the requests array
     */
    public Boolean addRequest(Request r) {
        Boolean added = false;

        if(r != null){
            this.requests.add(r);
            added = true;
        }

        return added;
    }

    /**
     * Adds a payment to this user
     */
    public Boolean addPayment(Payment p) {

        Boolean added = false;

        if(p != null){
            this.payments.add(p);
            added = true;
        }

        return added;
    }

    /**
     * Returns all payments for this user
     */
    public ArrayList<Payment> getPayments() {
        return this.payments;
    }


    /**
     * returns the notes array
     */
    public ArrayList<Note> getNotes() {
        return this.notes;
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
     * Gets the id of the current user
     * @return the id of the user
     */
    public int getUserId()
    {
        return this.user_id;
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