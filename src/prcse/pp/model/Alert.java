package prcse.pp.model;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 02/04/14
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class Alert extends Message {

    private Property property;

    /**
     * Alert constructor, allows the landlord to track whether payments have been missed on houses
     * or if a user wants to view a property
     * @param tenant the tenant who the alert is against
     * @param message the message body
     * @param p the property the alert is against
     */
    public Alert(Tenant tenant, String message, Property p, String date, int id) {
        super(tenant, message, date, id);
        this.type     = type.ALERT;
        this.property = p;
    }
}