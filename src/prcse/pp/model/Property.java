package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.util.ArrayList;

/**
 * Property class, allows
 * @author PRCSE
 */
public class Property implements ISubject{

    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private String city;
    private String teleNumber;
    private Integer noOfRooms;
    private PropertyStatus propStatus;

    // arraylist of payments
    private ArrayList<Payment> paymentList;
    // arraylist of rooms associated with this property
    private ArrayList<Room> rooms;
    private transient ArrayList<IObserver> observerList;


    public Property(){

        this.addressLine1 = "";
        this.addressLine2 = "";
        this.postcode = "";
        this.city = "";
        this.teleNumber = "";
        this.noOfRooms = 0;
        this.propStatus = PropertyStatus.VACANT;
        this.observerList = new ArrayList<>();
    }

    // constructor to accept one address line as second is optional.
    public Property(String address1, String postcode, String city, String teleNum, int noRooms, PropertyStatus status){

        this.addressLine1 = address1;
        this.addressLine2 = "";
        this.postcode = postcode;
        this.city = city;
        this.teleNumber = teleNum;
        this.noOfRooms = noRooms;
        this.propStatus = PropertyStatus.VACANT;
    }

    // constructor to accept both address lines.
    public Property(String address1, String address2, String postcode, String city, String teleNum, int noRooms, PropertyStatus status){

        this.addressLine1 = address1;
        this.addressLine2 = address2;
        this.postcode = postcode;
        this.city = city;
        this.teleNumber = teleNum;
        this.noOfRooms = noRooms;
        this.propStatus = PropertyStatus.VACANT;
    }

    // change the status of a property to 'FULL'
    public void occupied() {
        this.propStatus = PropertyStatus.FULL;
        this.notifyObservers();
    }

    // reset property status to vacant.
    public void vacant() {
        this.propStatus = PropertyStatus.VACANT;
        this.notifyObservers();
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

