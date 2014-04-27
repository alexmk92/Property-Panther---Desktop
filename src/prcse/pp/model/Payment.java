package prcse.pp.model;

import prcse.pp.controller.ScreensFramework;
import prcse.pp.model.observer.IObserver;
import prcse.pp.model.observer.ISubject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;

/**
 * Creates a new Payment object
 *
 * @author PRCSE
 */
public class Payment implements ISubject, Serializable {

    /**
     * Variables which describe a payment
     */
    private double        amount;
    private Date          date_paid;
    private Date          date_due;
    private String        reference_id;
    private int           property_id;
    private int           id;
    private String        paymentStatus;
    private Tenant        tenant;

    // Arraylist of Observer
    private ArrayList<IObserver> observers = null;

    /**
     * Creates a new payment object
     * @param thisAmount the amount paid
     * @param t - the tenant who made the payment
     */
    public Payment(Tenant t, double thisAmount, Date date, int idIn, String status, String ref_id, Date payment_due, int prop_id)
    {
        this.amount    = formatPayment(thisAmount);

        // Check the payment date
        if(date == null){
            this.date_paid = null;
        } else {
            this.date_paid = date;
        }

        // Check the due date
        if(payment_due == null){
            this.date_due = setToday();
        } else {
            this.date_due = payment_due;
        }

        this.tenant = t;
        this.reference_id = ref_id;
        this.property_id = prop_id;
        this.id = idIn;
        this.paymentStatus = status;
    }

    /**
     * Constructor for a new payment that is not being loaded from the db
     */
    public Payment(Tenant t, double amount, String status, Property p) {
        this.tenant = t;
        this.amount = amount;
        this.paymentStatus = status;
        this.property_id = p.getPropertyId();
        this.date_paid = null;
        this.date_due = nextMonth();
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
     * Sets the date to be a month in the future from today
     */
    public Date nextMonth() {
        Date date = null;

        GregorianCalendar a = new GregorianCalendar();
        a.set(Calendar.MONTH, a.get(Calendar.MONTH) + 1);

        date = a.getTime();

        return date;
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
     * @return A String representing the date when they payment was set
     */
    public String getDateAsString() {
        String result = "";
        if (null != this.date_paid) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
            result = formatter.format(this.date_paid);
        } else {
            result = this.getStatus();
        }
        return result;
    }

    /**
     * Accessor method that retrieves the date on which this leave request start
     * as a string suitable for display in the format dd/MM/yyyy
     *
     * @return A String representing the date on which the payment was received
     */
    public String getDueDateAsString() {
        String result = "";
        if (null != this.date_due) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
            result = formatter.format(this.date_due);
        } else {
            result = this.getStatus();
        }
        return result;
    }

    /**
     * Returns the tenant who paid
     */
    public String getPayee(){
        return this.tenant.getName();
    }


    /**
     * Gets the id of the payment
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the amount paid
     */
    public String getAmount() {
        return String.valueOf(this.amount);
    }

    /**
     * Returns the current status of this payment
     */
    public String getStatus() {
        return this.paymentStatus;
    }

    /**
     * Returns the payee object
     */
    public Tenant getTenant(){
        return this.tenant;
    }


    /**
     * Formats the payment amount to 2.d.p
     * @return the formatted payment amount
     */
    private double formatPayment(double amount)
    {
        return Math.round(amount * 100.00) / 100.0;
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
