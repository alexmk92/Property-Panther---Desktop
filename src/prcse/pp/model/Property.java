package prcse.pp.model;

import prcse.pp.controller.ScreensFramework;
import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;
import prcse.pp.model.RoomStatus;
import prcse.pp.model.PropertyStatus;
import prcse.pp.model.Room;


import java.util.ArrayList;

/**
 * Property class, allows
 * @author PRCSE
 */
public class Property implements ISubject{

    /**
     * Variables which describe a property
     */
    private int            propId;
    private String         trackingCode;
    private String         addressLine1;
    private String         addressLine2;
    private String         postcode;
    private String         district;
    private String         city;
    private String         details;
    private int            noOfRooms;
    private PropertyStatus propStatus;

    // arraylist of payments associated with this property
    private ArrayList<Payment> payments;

    // arraylist of rooms associated with this property
    private RoomList rooms;

    /**
     * New arraylist of Observers whom Observer properties
     */
    private transient ArrayList<IObserver> observerList;

    /**
     * Empty property object to account for null items from the database
     */
    public Property()
    {
        this.propId = 0;
        this.trackingCode = "";
        this.addressLine1 = "";
        this.addressLine2 = "";
        this.postcode     = "";
        this.city         = "";
        this.details      = "";
        this.noOfRooms    = 0;
        this.propStatus   = getStatus();

        this.payments = new ArrayList<Payment>();
        this.rooms    = new RoomList();
    }

    /**
     * Constructor to create a new Property
     * @param address1 address line 1
     * @paran address2 address line 2 (can be null)
     * @param postcode the properties postcode
     * @param city  the properties city
     * @param teleNum the properties landline number (if applicable)
     * @param noRooms the number of rooms in that property
     */
    public Property(int propertyId, String trackCode, String address1, String address2, String postcode,
                    String district, String city, String details, int noRooms)
    {
        // Initialise a new array list of rooms
        this.rooms = new RoomList();

        // Initialise a new payment list
        this.payments = new ArrayList<Payment>();

        this.propId       = propertyId;
        this.trackingCode = trackCode;
        this.addressLine1 = address1;
        this.addressLine2 = address2;
        this.postcode = postcode;
        this.city = city;
        this.details = details;
        this.noOfRooms = this.rooms.getNumRooms();
        this.propStatus = getStatus();
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
     * Sets the status of the Property to VACANT if all
     * the rooms are VACANT
     * @param currStatus - Checks if the current status matches before running an
     *                     update query
     * @return True if all rooms are vacant, else return false.
     */
    public Boolean vacant(PropertyStatus currStatus) {

        Boolean allVacant   = false;    // Are all rooms vacant
        int     successFlag = 0;        // How many rooms are vacant?

        // Check to see whether the room at index i is VACANT
        for(int i = 0; i < rooms.getNumRooms(); i++)
        {
            Room currRoom = rooms.getRoomAt(i);

            // If room i is vacant, increment successFlag by 1
            if(currRoom.getStatus() == RoomStatus.VACANT) {
                successFlag++;
            }
        }

        // If all rooms are VACANT, update the Property Status to VACANT, else call occupied()
        if(successFlag == rooms.getNumRooms())
        {
            this.propStatus = PropertyStatus.VACANT;
            if(this.propStatus != currStatus)  {
                updateStatus("VACANT");
                this.notifyObservers();
            }
        } else {
            if(this.propStatus != currStatus) {
                this.occupied();
            }
        }

        return allVacant;
    }

    /**
     * Changes the status of the property to FULL
     */
    public void occupied() {
        this.propStatus = PropertyStatus.FULL;
        updateStatus("FULL");
        this.notifyObservers();
    }

    /**
     * Updates the status of the property on a new thread dependent on
     * the string variable passed - use a runnable to process in background
     * @param status - FULL or VACANT
     */
    private void updateStatus(String status) {
        String query = "UPDATE properties SET prop_status = '" + status + "' WHERE property_id = " + this.getPropertyId();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ScreensFramework.db.update(query);
            }
        }).start();
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
            for(int i = 0; i < rooms.getNumRooms(); i++)
            {
                // If the room objects match then set the requestedRoom to the room object
                if(thisRoom == rooms.getRoomAt(i))
                {
                    requestedRoom = thisRoom;
                }
            }
        }

        // Return the requested Room object
        return requestedRoom;
    }

    /**
     * Returns the rooms array belonging to this property
     * converts to an arraylist to be used in an observable list
     * @return arraylist of room objects belonging to this property
     */
    public ArrayList<Room> getRooms() {
        ArrayList<Room> allRooms = new ArrayList<>();

        for(int i = 0; i < rooms.getNumRooms(); i++) {
            Room r = rooms.getRoomAt(i);
            allRooms.add(r);
        }

        return allRooms;
    }

    /**
     * Adds a new room to the system using the RoomList classes
     * @param r the room we wish to add
     * @return true if the room is added else false
     */
    public Boolean addRoom(Room r)
    {
        Boolean roomAdded = false;

        if(r != null && this.rooms.addRoom(r))
        {
            roomAdded = true;
        }

        return roomAdded;
    }

    /**
     * Returns a count of the number of rooms
     * @return total rooms in the rooms array
     */
    public int numRooms()
    {
        return this.rooms.getNumRooms();
    }

    /**
     * Gets the room at the given index using the
     * room list class
     * @return r - the room object we find an empty room
     */
    public Room getRoomAt(int index)
    {
        Room r = new Room();

        // Get the room using the UserList class methods
        if(index >= 0) {
            r = rooms.getRoomAt(index);
        }

        // the room object
        return r;
    }

    /**
     * Getters and setters
     */
    public int getPropertyId()
    {
        return propId;
    }

    public void setPropertyId(int propertyId)
    {
        this.propId = propertyId;
    }

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public PropertyStatus getStatus() {

        int vacantRooms = 0;

        if(rooms != null)
        {
            for(int i = 0; i < rooms.getNumRooms(); i++)
            {
                Room r = rooms.getRoomAt(i);

                if(r.getStatus() == RoomStatus.VACANT);
                    vacantRooms++;
            }

            if(vacantRooms >= rooms.getNumRooms())
                propStatus = PropertyStatus.VACANT;
            else
                propStatus = PropertyStatus.FULL;
        }
        return propStatus;
    }

    public void setPropStatus(PropertyStatus propStatus) {
        this.propStatus = propStatus;
    }
    public String  getFullAddress() {
        return addressLine1 + " " + addressLine2 + " " + city + " " + details + " " + postcode + ".";
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

