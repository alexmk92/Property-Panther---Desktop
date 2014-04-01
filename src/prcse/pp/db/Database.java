package prcse.pp.db;

import prcse.pp.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Create a new connection to the database and handle any SQL queries through
 * its methods.
 */
public class Database {

    private String db_host;
    private String db_user;
    private String db_pass;

    /**
     * Create the connection
     * @param host the host address of the database
     * @param user the user we are logging in as
     * @param password the password for that user
     */
    public Database(String host, String user, String password)
    {
        // set default connection
        if(host == null && user == null && password == null){
            this.db_host = "jdbc:oracle:thin:@tom.uopnet.plymouth.ac.uk:1521:orcl";
            this.db_user = "PRCSE";
            this.db_pass = "PRCSE";
        } else {
            this.db_host = host;
            this.db_user = user;
            this.db_pass = password;
        }

    }

    /**
     * Builds all objects in the database in precedence
     * @return true if the objects were built, else return false
     */
    public Boolean buildObjects()
    {
        Boolean objectsBuilt = false;
        int buildCount = 0;

        if(objectsBuilt == false)
        {
            if(buildProperties()) { buildCount++; }
            if(buildRooms()) { buildCount++; }
            //buildRequests();
            //buildTracking();
            //buildPayments();
            //buildNotes();
            //buildMessages();
            //buildUsers();

            // Have all of the objects been built?
            if(buildCount >= 2) {
                objectsBuilt = true;
            } else {
                objectsBuilt = false;
            }
        }

        return objectsBuilt;
    }

    /**
     * Queries the database and builds all property objects
     * @return true if all property objects have been made, else return false
     */
    public Boolean buildProperties(){
        Boolean propertiesBuilt = false;
        try {
            PropertyList propertyList = new PropertyList();
            Connection con = DriverManager.getConnection(this.db_host, this.db_user, this.db_pass);
            Statement  st  = con.createStatement();
            ResultSet  res = st.executeQuery("SELECT * FROM properties");

            while(res.next()) {
                Property p = new Property(res.getInt("property_id"), res.getString("prop_track_code"), res.getString("addr_line_1"),
                        res.getString("addr_line_2"), res.getString("addr_postcode"), res.getString("addr_district"), res.getString("city_name"),
                        res.getString("prop_details"), res.getInt("prop_num_rooms"));

                propertyList.addProperty(p);
            }

            propertiesBuilt = true;
            System.out.println(propertyList.size());
        } catch (SQLException e) {
            System.out.println("Error handling query: " + e.getMessage());
            propertiesBuilt = false;
        }

        return propertiesBuilt;
    }

    /**
     * Queries the database and builds all room objects
     * @return true if all room objects have been made, else return false
     */
    public Boolean buildRooms(){
        Boolean roomsBuilt = false;
        try {
            RoomList roomList = new RoomList();
            Connection con = DriverManager.getConnection(this.db_host, this.db_user, this.db_pass);
            Statement  st  = con.createStatement();
            ResultSet  res = st.executeQuery("SELECT * FROM rooms");


            while(res.next()) {
                Room r = new Room(res.getInt("room_id"), res.getInt("property_id"), res.getString("room_price"),
                                  res.getString("room_details"));

                roomList.addRoom(r);
            }

            roomsBuilt = true;
            System.out.println(roomList.size());

        } catch (SQLException e) {
            System.out.println("Error handling query: " + e.getMessage());
            roomsBuilt = false;
        }

        return roomsBuilt;
    }



    /**
     * Build all user objects for the system
     */
    public void buildUsers(){
        try {
            UserList tenantList = new UserList();
            Connection con = DriverManager.getConnection(this.db_host, this.db_user, this.db_pass);
            Statement  st  = con.createStatement();
            ResultSet  res = st.executeQuery("SELECT * FROM users");

            while(res.next()) {
                Property p = null;
                Room     r = new Room();
                Tenant u = new Tenant();

                tenantList.addUser(u);
            }

            System.out.println(tenantList.size());
            System.out.println(tenantList.getUserAt(0).getName());
            System.out.println(tenantList.getUserAt(1).getName());
            System.out.println(tenantList.getUserAt(2).getName());
            System.out.println(tenantList.getUserAt(3).getName());
            System.out.println(tenantList.getUserAt(4).getName());
        } catch (SQLException e) {
            System.out.println("Error handling query: " + e.getMessage());
        }
    }


    // Getters and setters
    public String getDb_host() {
        return db_host;
    }

    public void setDb_host(String db_host) {
        this.db_host = db_host;
    }

    public String getDb_user() {
        return db_user;
    }

    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    public String getDb_pass() {
        return db_pass;
    }

    public void setDb_pass(String db_pass) {
        this.db_pass = db_pass;
    }

}
