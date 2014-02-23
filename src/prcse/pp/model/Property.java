package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.util.ArrayList;

/**
 * Property class, allows
 * @author PRCSE
 */
public class Property implements ISubject{

    /**
     * Variables which describe a property
     */
    private String         addressLine1;
    private String         addressLine2;
    private String         postcode;
    private String         city;
    private String         teleNumber;
    private Integer        noOfRooms;
    private PropertyStatus propStatus;

    // arraylist of payments associated with this property
    private ArrayList<Payment> payments;

    // arraylist of rooms associated with this property
    private ArrayList<Room> rooms;

    /**
     * New arraylist of Observers whom Observer properties
     */
    private transient ArrayList<IObserver> observerList;

    /**
     * Constructor to create a new Property
     * @param address1 address line 1
     * @paran address2 address line 2 (can be null)
     * @param postcode the properties postcode
     * @param city  the properties city
     * @param teleNum the properties landline number (if applicable)
     * @param noRooms the number of rooms in that property
     */
    public Property(String address1, String address2, String postcode, String city, String teleNum,
                    int noRooms, PropertyStatus status)
    {
        this.addressLine1 = address1;
        this.addressLine2 = address2;
        this.postcode = postcode;
        this.city = city;
        this.teleNumber = teleNum;
        this.noOfRooms = noRooms;
        this.propStatus = status;

        // Initialise a new payment list
        this.payments = new ArrayList<Payment>();

        // Initialise a new array list of rooms
        this.rooms = new ArrayList<Room>();
    }

    /**
     * Make a Payment against this property.
     * @param amount the Payment amount
     * @param t the Tenant making the payment
     * @return True if the payment was made, else return false.
     */
    public Boolean makePayment(double amount, Tenant t)
    {
        // Assume this method would fail
        Boolean result = false;

        // Create a new payment object
        Payment thisPayment = new Payment(amount, t);

        // Check the variables are set and perform adding the payment to the property
        if(amount >= 0 && null != t)
        {
            // Add payment to property payments array list
            this.payments.add(thisPayment);

            // Add payment to the users payment array list record
            t.payments.add(thisPayment);
        }

        // True or False
        return result;
    }

    /**
     * Returns this Property object
     * @return this - this Property object
     */
    public Property getProperty()
    {
        return this;
    }

    /**
     * Changes the status of the property to FULL
     */
    public void occupied() {
        this.propStatus = PropertyStatus.FULL;
        this.notifyObservers();
    }

    /**
     * Sets the status of the Property to VACANT if all
     * the rooms are VACANT
     * @return True if all rooms are vacant, else return false.
     */
    public Boolean vacant() {

        Boolean allVacant   = false;    // Are all rooms vacant
        int     successFlag = 0;        // How many rooms are vacant?

        // Check to see whether the room at index i is VACANT
        for(int i = 0; i < rooms.size(); i++)
        {
            Room currRoom = rooms.get(i);

            // If room i is vacant, increment successFlag by 1
            if(currRoom.getStatus() == RoomStatus.VACANT)
                successFlag++;
        }

        // If all rooms are VACANT, update the Property Status
        if(successFlag == rooms.size())
        {
            this.propStatus = PropertyStatus.VACANT;
            this.notifyObservers();
        }

        return allVacant;
    }

    /**
     * Gets the specified Room from the Properties Room ArrayList with
     * a given Room ID
     * @param thisRoom the Room requested
     * @return the Room object requested, else return null
     */
    public Room getRoom(Room thisRoom)
    {
        // Create a nulled Room object
        Room requestedRoom = null;

        // Check whether the request room object is valid
        if(null != thisRoom)
        {
            // Loop through the rooms array
            for(int i = 0; i < rooms.size(); i++)
            {
                // If the room objects match then set the requestedRoom to the room object
                if(thisRoom == rooms.get(i))
                {
                    requestedRoom = thisRoom;
                }
            }
        }

        // Return the requested Room object
        return requestedRoom;
    }

    /**
     * Getters and setters
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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

    public String getTeleNumber() {
        return teleNumber;
    }

    public void setTeleNumber(String teleNumber) {
        this.teleNumber = teleNumber;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public PropertyStatus getPropStatus() {
        return propStatus;
    }

    public void setPropStatus(PropertyStatus propStatus) {
        this.propStatus = propStatus;
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

