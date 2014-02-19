package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A collection of all user objects
 * @author PRCSE
 */
public class UserList implements ISubject, IObserver, Serializable {

    private ArrayList<User> list = new ArrayList();
    private transient ArrayList<IObserver> observerList;


    public ArrayList<IObserver> getObservers()
    {
        ArrayList<IObserver> arlResult = new ArrayList<>();
        for (IObserver currObserver : observerList)
        {
            arlResult.add(currObserver);
        }
        return arlResult;
    }

    // empty constructor
    public UserList(){
        this.list = new ArrayList<>();
        this.observerList = new ArrayList<>();
    }


    public void addUser(User newUser){
        if(null != newUser){
            this.list.add(newUser);
            newUser.registerObserver(this);
            this.notifyObservers();
        }
    }

    public User removerUser(int index){
        User result = null;
        if(index >= 0 && index < this.list.size()){
            result = this.list.remove(index);
            result.removeObserver(this);
            this.notifyObservers();
        }
        return result;
    }


    // Not sure if we need this function
   /* public String[] getAllUsers(){
        String[] result = new String[this.list.size()];
        for(int i = 0; i < this.list.size(); i++){
            User record = this.list.get(i);
            if(null != record){
                result[i] = record.getName();
            }
        }
        return result;
    }  */


    public User getUserAt(int index){
        User result = null;
        if(index >= 0 && index < this.list.size()){
            result = this.list.get(index);
        }
        return result;
    }


    public int size(){
        return this.list.size();
    }


    @Override
    public void update() {
        this.notifyObservers();
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