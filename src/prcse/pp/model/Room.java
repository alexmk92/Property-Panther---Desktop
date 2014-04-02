package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;
import prcse.pp.db.Database;
import prcse.pp.controller.ScreensFramework;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Allows us to build a Room object
 * @author PRCSE
 */
public class Room implements ISubject, Serializable {

    /**
     * Variables that describe a room
     */
    private int        roomId;
    private int        propertyId;
    private String     price;
    private int        contractLength;
    private String     details;
    private Tenant     tenant;
    private RoomStatus status;

    // Creates an ArrayList of Observers
    private ArrayList<IObserver> observers = null;

    /**
     * Empty constructor to support unset rooms
     */
    public Room(){
        this.roomId         = 0;
        this.propertyId     = 0;
        this.tenant         = null;
        this.price          = "0";
        this.details        = "";
        this.contractLength = 0;

        this.status         = getStatus();
    }

    /**
     * Assigns a price and any details to a room as well as setting its default
     * status as VACANT,  by default it will not have a tenant
     * @param price the price per month of the room
     * @param details any details associated with the room
     */
    public Room(int roomId, int propertyId, String price, String details){
        this.roomId         = roomId;
        this.propertyId     = propertyId;
        this.tenant         = null;
        this.price          = price;
        this.details        = details;
        this.contractLength = 12;
        this.status         = getStatus();
    }

    /**
     * Returns this room object
     * @return this - this Room object
     */
    public Room getRoom()
    {
        return this;
    }

    /**
     * Specifies that the room is now occupied and describes the tenant
     * who occupies that room.
     * @param tenant the tenant who is renting the room
     */
    public void occupied(Tenant tenant){
        this.tenant = tenant;
        this.status = RoomStatus.OCCUPIED;
    }

    /**
     * Removes the tenant from the current room and sets the rooms status
     * to VACANT, flagging a new user can rent it.
     */
    public void vacant()
    {
        this.tenant = null;
        this.status = RoomStatus.VACANT;
    }

    /**
     * Returns the tenant object, for the tenant whom occupies this room
     * @return a tenant Object if one is found, else returns null.
     */
    public Tenant getTenant()
    {
        if(null != this.tenant)
            return this.tenant;
        else
            return null;
    }

    /**
     * returns a string containing any details of the Room
     * @return
     */
    public String getDetails()
    {
        return this.details;
    }

    /**
     * returns the price of the room
     * @return int price
     */
    public double getPrice()
    {
        double thisPrice;
        thisPrice = Double.parseDouble(this.price);

        return thisPrice;
    }

    /**
     * Returns the roomId of this room
     * @return int roomId
     */
    public int getRoomId()
    {
        return roomId;
    }

    /**
     * Returns the formatted status of the room
     * @return returns the room status
     */
    public RoomStatus getStatus()
    {
        Tenant thisTenant = this.tenant;
        RoomStatus status;

        if(thisTenant != null)
        {
            this.occupied(thisTenant);
            status = RoomStatus.OCCUPIED;
        } else {
            this.vacant();
            status = RoomStatus.VACANT;
        }

        return status;
    }

    /**
     * Returns the amount of months remaining on the
     * contract
     */
    public int getContractLength()
    {
        return this.contractLength;
    }


    /**
     * ToString() output of this class
     * @return
     */
    @Override
    public String toString() {
        return "Room{" + "tenant= " + tenant.getName() + ", price=" + price
                + ", status=" + status.toString() + '}';
    }

    /**
     * Registers a new Room observer to the Room observer array list
     * @param o the Observer we are registering
     * @return true if the Observer is registered, else returns false
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
     * Removes the given Room observer from the array list
     * @param o the observer we are removing
     * @return true if the Observer is removed, else false
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