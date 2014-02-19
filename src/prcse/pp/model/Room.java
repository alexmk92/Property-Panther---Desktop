package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

/**
 *
 * @author User
 */
public class Room implements ISubject {

    private Property property;
    private int price;
    private String details;
    private RoomStatus status;

    public Room(){
        this.property = null;
        this.price = 0;
        this.details = "";
    }

    public Room(Property property, int price, String details){
        this.property = property;
        this.price = price;
        this.details = details;
        this.status = RoomStatus.VACANT;
    }

    public void occupied(){
        this.status = RoomStatus.OCCUPIED;
    }

    @Override
    public Boolean registerObserver(IObserver o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}