package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Create a new User object and allow it to be Observed
 * by the Observer pattern
 */
public class User implements ISubject, Serializable{

    protected String title;
    protected String forename;
    protected String surname;
    protected String email;
    protected String phone;
    protected String addr_line_1;
    protected String addr_line_2;
    protected String postcode;
    protected String city;
    private UserPermission permission;
    private Property property;
    private Room room;

    private transient ArrayList<IObserver> observerList;
    private ArrayList<Message> messages;
    private ArrayList<Payment> payments;

    /**
     * test
     * @param forename
     * @param surname
     */
    public User(String forename, String surname)
    {
        this.forename = forename;
        this.surname  = surname;
    }

    /**
     * Empty constructor for a guest user
     */
    public User() {
        this.title       = "";
        this.forename    = "";
        this.surname     = "";
        this.email       = "";
        this.phone       = "";
        this.addr_line_1 = "";
        this.addr_line_2 = "";
        this.postcode    = "";
        this.city        = "";
    }

    /**
     * Constructor for a user who does not rent a Room or Property
     * @param title users title
     * @param forename users first name
     * @param surname users last name
     * @param email  users email
     * @param number users home/mobile number
     * @param addr_1 users home address line ( NOT RENTED )
     * @param addr_2 users optional second address line ( NOT RENTED )
     * @param postcode users postcode ( NOT RENTED )
     * @param city users home city where this house resides ( NOT RENTED )
     */
    public User(String title, String forename, String surname, String email, String number,
                String addr_1, String addr_2, String postcode, String city){
        this.title       = title;
        this.forename    = forename;
        this.surname     = surname;
        this.email       = email;
        this.phone       = number;
        this.addr_line_1 = addr_1;
        this.addr_line_2 = addr_2;
        this.postcode    = postcode;
        this.city        = city;
        this.permission  = UserPermission.USER;
    }

    /**
     * Constructor for a user who rents a Room or Property
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
    public User(String title, String forename, String surname, String email, String number,
                String addr_1, String addr_2, String postcode, String city, Property property, Room room){
        this.title       = title;
        this.forename    = forename;
        this.surname     = surname;
        this.email       = email;
        this.phone       = number;
        this.addr_line_1 = addr_1;
        this.addr_line_2 = addr_2;
        this.postcode    = postcode;
        this.city        = city;
        this.permission  = UserPermission.USER;
        this.property    = property;
        this.room        = room;
        // sets status to occupied if the user has a room.
        this.room.occupied();
    }

    /**
     * Allows an admin to set a given users permissions
     * @param newPermission the permission level we want to set
     * @param thisUser the user who's privileges we are changing
     */
    public void grantPermission(UserPermission newPermission, User thisUser){
        if(this.permission == UserPermission.ADMIN)
            thisUser.permission = newPermission;
        else
            System.out.println("Sorry, only admins can escalate permissions.");
    }

    /**
     * Revokes the permissions of a user and sets them back to
     * default permissions
     * @param thisUser the user who is having their permissions amended
     */
    public void revokePermissions(User thisUser){
        if(this.permission == UserPermission.ADMIN)
            thisUser.permission = UserPermission.USER;
        else
            System.out.println("Sorry, only admins can revoke permissions.");
    }


    // Getters and setters
    public String getName(){
        String result = "UNKNOWN";

        if(null != this.forename && null != this.surname){
            result = this.surname + ", " + this.forename;
        }
        return result;
    }

    public String getAllDetails(){
        String result = "UNKNOWN";

        if(null != this.forename && null != this.surname && null != this.email && null != this.phone){
            result = this.surname + ", " + this.forename + "email: " + this.email + "Telephone Number: " + this.phone;
        }
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr_line_1() {
        return addr_line_1;
    }

    public void setAddr_line_1(String addr_line_1) {
        this.addr_line_1 = addr_line_1;
    }

    public String getAddr_line_2() {
        return addr_line_2;
    }

    public void setAddr_line_2(String addr_line_2) {
        this.addr_line_2 = addr_line_2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserPermission getPermission() {
        return permission;
    }

    public void setPermission(UserPermission permission) {
        this.permission = permission;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<IObserver> getObserverList() {
        return observerList;
    }

    public void setObserverList(ArrayList<IObserver> observerList) {
        this.observerList = observerList;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public String getForename() {
        return forename;

    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return phone;
    }

    public void setNumber(String number) {
        this.phone = number;
    }

    /**
     * Registers a new Observer for this User
     * @param o the Observer we are observing with
     * @return true if the Observer was registered
     */
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

    /**
     * Removes the Observer for this User
     * @param o the Observer we are observing with
     * @return true if the Observer was registered
     */
    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean result = false;
        if(null != o){
            result = this.observerList.remove(o);
        }
        return result;
    }


    // this wont work, coomes up with errors if i add the code needed.
    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}