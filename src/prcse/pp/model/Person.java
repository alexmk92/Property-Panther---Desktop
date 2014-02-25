package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 22/02/14
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class Person implements Serializable, ISubject {

    private ArrayList<IObserver> observers = null;

    /**
     * Variables which describe a "Person" object
     */
    protected String         title;
    protected String         forename;
    protected String         surname;
    protected String         email;
    protected String         phone;
    protected String         addr_line_1;
    protected String         addr_line_2;
    protected String         postcode;
    protected String         city;
    protected UserPermission permission;


    /**
     * Constructor accepting the forename and surname of the new Person
     * @param forename - String being the new Persons forename
     * @param surname - String being the new Persons surname
     */
    public Person(String forename, String surname)
    {
        this.forename = forename;
        this.surname  = surname;
    }

    /**
     * Empty constructor for a guest user
     */
    public Person() {
        this.title       = "UNKNOWN";
        this.forename    = "GUEST";
        this.surname     = "GUEST";
        this.email       = "NULL";
        this.phone       = "NULL";
        this.addr_line_1 = "NULL";
        this.addr_line_2 = "NULL";
        this.postcode    = "NULL";
        this.city        = "NULL";

        this.permission  = permission.GUEST;
    }

    /**
     * Constructor for a registered user who does not rent a Room or Property
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
    public Person(String title, String forename, String surname, String email, String number,
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


    // Getters and setters
    public String getName(){
        String result = "UNKNOWN";

        if(null != this.forename && null != this.surname){
            result = this.forename + " " + this.surname;
        }
        return result;
    }

    public String getAllDetails(){
        String result = "UNKNOWN";

        if(null != this.forename && null != this.surname && null != this.email && null != this.phone){
            result = this.surname + ", " + this.forename + " email: " + this.email + " Telephone Number: " + this.phone;
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
     * Formats the .toString() of the Person object
     * @return the formatted .toString() string
     */
    @Override
    public String toString() {
        return "Person{" + "forename=" + forename + ", surname=" + surname + '}';
    }

    /**
     * Registers a new Person observer to the observer array list
     * @param o
     * @return
     */
    @Override
    public Boolean registerObserver(IObserver o) {
        Boolean blnAdded = false;
        //Validate that observer exists
        if (o != null) {
            //If observer list not initialised create it
            if (this.observers == null) {
                this.observers = new ArrayList<>();
            }
            //Add the observer to the list of registered observers if not already registered
            if(!this.observers.contains(o)){
                blnAdded = this.observers.add(o);
            }
        }
        //Return the result
        return blnAdded;
    }

    /**
     * Removes the given Person observer from the array list
     * @param o
     * @return
     */
    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean blnRemoved = false;
        //Validate we have something to remove
        if (o != null) {
            if(this.observers != null && this.observers.size() > 0){
                blnRemoved = this.observers.remove(o);
            }
        }
        return blnRemoved;
    }

    /**
     * Notifys all observers in this ArrayList of a change
     */
    @Override
    public void notifyObservers() {
        //Ensure we have a valid list of observers
        if (this.observers != null && this.observers.size() > 0) {
            //Start foreach loop
            for (IObserver currentObserver : this.observers) {
                //Call update on this observer
                currentObserver.update();
            }
        }
    }

}
