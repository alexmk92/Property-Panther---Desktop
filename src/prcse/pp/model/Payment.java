package prcse.pp.model;

import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Creates a new Payment object
 *
 * @author PRCSE
 */
public class Payment implements ISubject, Serializable {

    /**
     * Variables which describe a payment
     */
    private double   amount;
    private Date     date_paid;
    private Tenant   tenant;

    // Arraylist of Observer
    private ArrayList<IObserver> observers = null;


    /**
     * Creates a new payment object
     * @param thisAmount the amount paid
     * @param thisTenant the tenant who paid the bill
     */
    public Payment(double thisAmount, Tenant thisTenant)
    {
        this.amount    = formatPayment(thisAmount);
        this.tenant    = thisTenant;
        this.date_paid = setToday();
    }

    /**
     * Sets the Date object to todays date
     * @return a Date object containing todays date
     */
    public Date setToday()
    {
        Calendar cal = new GregorianCalendar();
        Date todaysDate = cal.getTime();

        return todaysDate;
    }

    /**
     * Accessor to retrieve the date object encapsulating the date on which the
     * leave is requested to start
     *
     * @return the Date object representing the day on which the leave starts
     */
    public Date getDatePaid() {
        return date_paid;
    }

    /**
     * Accessor to set the date object encapsulating the date on which the leave
     * is requested to start
     *
     * @param datePaid the Date object representing the new date on which this
     * leave request starts.
     */
    public void setDatePaid(Date datePaid) {
        this.date_paid= datePaid;
        this.notifyObservers();
    }

    /**
     * Accessor method that retrieves the date on which this leave request start
     * as a string suitable for display in the format dd/MM/yyyy
     *
     * @return A String representing the date on which the leave starts.
     */
    public String getDateAsString() {
        String result = "";
        if (null != this.date_paid) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            result = formatter.format(this.date_paid);
        }
        return result;
    }

    /**
     * Formats the payment amount to 2.d.p
     * @return the formatted payment amount
     */
    private double formatPayment(double amount)
    {
        return Math.round(amount * 100.0) / 100.0;
    }

    /**
     * Formats the output string of the Payment class
     * @return the formatted output string
     */
    @Override
    public String toString() {
        return "Payment{" + "amount=" + amount + ".}";
    }

    /**
     * Registers a new Payment observer to the Payment observer array list
     * @param o
     * @return
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
     * Removes the given Payment observer from the array list
     * @param o
     * @return
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
