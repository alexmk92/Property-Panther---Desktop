package prcse.pp.db;

import prcse.pp.model.User;

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

    private String     db_host;
    private String     db_user;
    private String     db_pass;

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
     * Perform a select query
     * @return
     */
    public void buildUsers(){
        ArrayList<ResultSet> results = new ArrayList<>();
        String query = "";
        try {
            Connection con = DriverManager.getConnection(this.db_host, this.db_user, this.db_pass);
            Statement  st  = con.createStatement();
            ResultSet  res = st.executeQuery(query);

            while(res.next()) {
                results.add(res);
            }
            res.close();
            con.close();
            System.out.println(res.getRow());
        } catch (SQLException e) {
            System.out.println("Error handling query: " + e.getMessage());
        }
    }


    // Demo some system out stuff here for tomorrow
    public void connectionDemo(){

        ArrayList<User> userList = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(this.db_host, this.db_user, this.db_pass);
            Statement  st  = con.createStatement();
            ResultSet  res = st.executeQuery("SELECT * FROM users");

            while(res.next()) {
                User u = new User();
                u.setForename(res.getString("user_forename"));
                u.setSurname(res.getString("user_surname"));
                userList.add(u);
            }

            System.out.println(userList.size());
            System.out.println(userList.get(0).getName());
            System.out.println(userList.get(1).getName());
            System.out.println(userList.get(2).getName());
            System.out.println(userList.get(3).getName());
            System.out.println(userList.get(4).getName());
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
