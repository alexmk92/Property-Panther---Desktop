package prcse.pp.db;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 18/02/14
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {

    private String db_host;
    private String db_user;
    private String db_pass;

    public Database(String host, String user, String password)
    {
        this.db_host = host;
        this.db_user = user;
        this.db_pass = password;

        try {
            Connection con = DriverManager.getConnection(db_host, db_user, db_pass);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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
