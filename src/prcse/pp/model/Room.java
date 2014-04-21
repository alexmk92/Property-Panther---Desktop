package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;
import prcse.pp.db.Database;
import prcse.pp.controller.ScreensFramework;


import javax.xml.transform.Result;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        this.status         = RoomStatus.VACANT;
    }

    /**
     * Assigns a price and any details to a room as well as setting its default
     * status as VACANT,  by default it will not have a tenant
     * @param price the price per month of the room
     * @param details any details associated with the room
     */
    public Room(int roomId, int propertyId, String price, String details, String status){
        this.roomId         = roomId;
        this.propertyId     = propertyId;
        this.tenant         = null;
        this.price          = price;
        this.details        = details;
        this.contractLength = 12;
        setStatus(status);
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
     * Returns the current status of the room
     * @retrun roomState - the status of the room
     */
    public RoomStatus getStatus() {
        return this.status;
    }

    /**
     * Loads the tenant from the db by getting their id
     * @throws SQLException - no result set may be found, if not throw
     */
    public void loadTenant() throws SQLException, NullPointerException {
        // Get the users id
        String query = "SELECT user_id FROM users JOIN rooms ON users.user_prop_room = rooms.room_id WHERE user_prop_room = " + this.getRoomId();
        ResultSet a = ScreensFramework.db.query(query);

        // Query returns one result,
        while(a.next()) {
            int this_user = a.getInt("user_id");
            this.tenant = this_user > 0 ? ScreensFramework.tenants.getUserById(this_user) : null;
        }

    }

    /**
     * Sets the tenant using their user_id and retrives
     * them from the global user list, users are built before
     * rooms so value will be present
     * @throws NullPointerException - in the DB, user_id is nullable in rooms
     */
    private void setTenant(Tenant thisTenant) {
        this.tenant = thisTenant;
    }

    /**
     * Returns the tenant object set against this room
     */
    public Tenant getTenant() {
        return this.tenant != null ? this.tenant : null;
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
     * Sets the details of the room
     */
    public void setDetails(String details){
        this.details = details;
    }

    /**
     * Sets the price of the room
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Returns the current status as a string
     * @param currStatus - the current Room Status value (OCCUPIED or VACANT)
     * @return string value of the status object
     */
    public String getStatusAsString(RoomStatus currStatus) {
        String status = "";

        switch(currStatus){
            case OCCUPIED:
                status = "Occupied";
                break;
            case VACANT:
                status = "Vacant";
                break;
        }

        return status;
    }

    /**
     * Sets the status with a string object
     * @param statusIn - the status we are settings
     */
    public void setStatus(String statusIn) {
        switch(statusIn){
            case "VACANT":
                this.status = RoomStatus.VACANT;
                break;
            case "OCCUPIED":
                this.status = RoomStatus.OCCUPIED;
                break;
            default :
                this.status = RoomStatus.VACANT;
                break;
        }
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