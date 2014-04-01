package prcse.pp.model;

import prcse.pp.controller.ScreensFramework;
import prcse.pp.controller.Searcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of all room objects belonging to a property
 * @author PRCSE
 */
public class RoomList {

    private ArrayList<Room> roomList;

    /**
     * Default (empty) constructor, creates a new empty list of rooms
     */
    public RoomList(){
        this.roomList = new ArrayList<Room>();
    }


    /**
     * Accessor to add the provided Room object to the list of Rooms
     * @param newRoom - The Room object to add to the list
     */
    public void addRoom(Room newRoom){
        if(null != newRoom){
            this.roomList.add(newRoom);
        }
    }

    /**
     * Accessor to remove a Room from the Room list. The index parameter
     * identifies the Room to be removed.
     * @param index - A zero based index value specifying the Room to
     * remove from the list
     * @return The Room object removed from the list OR NULL if the index value
     * was invalid.
     */
    public Room removeRoomAt(int index){
        Room result = null;
        if(index >= 0 && index < this.roomList.size()){
            result = this.roomList.remove(index);
        }
        return result;
    }

    /**
     * Accessor to retrieve the Room object specified by the index parameter
     * @param index - A zero based index value specifying the Object required from the list
     * @return - The Room at the specified index or NULL if no user is found
     */
    public Room getRoomAt(int index){
        Room result = null;
        if(index >= 0 && index < this.roomList.size()){
            result = this.roomList.get(index);
        }
        return result;
    }

    /**
     * Accessor to retrieve the size of the Property list
     * @return - int being the number of Properties currently on the user list
     */
    public int size(){
        return this.roomList.size();
    }

}