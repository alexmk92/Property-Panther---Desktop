package prcse.pp.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Adds a new payment object to the Users payment array list, any insertion validation
 * is done on the DBMS
 * @author PRCSE
 */
public class Payment {

    private User user;
    private int amount;
    private Date date_paid;

    public Payment(){
        this.user = null;
        this.amount = 0;
    }

    /**
     * Creates a Payment record for this user
     * @param user
     * @param amount
     */
    public Payment(User user, int amount){
        this.user = user;
        this.amount = amount;
        this.date_paid = currDate();
    }


    /**
     * Set the payment time to the current DateTime
     * @return
     */
    private Date currDate()
    {
        Date date = Calendar.getInstance().getTime();

        return date;
    }
}
