package prcse.pp.model;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 02/04/14
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class Inbox extends Message {

    private Boolean seen;

    /**
     * Constructor for an inbox message
     * @param toTenant the user the message is addressed to
     * @param fromTenant the user the message is from
     * @param message the body of the message
     */
    public Inbox(Tenant toTenant, Tenant fromTenant, String message) {
        super(toTenant, fromTenant, message);
        this.type = type.INBOX;
        this.seen = false;
    }


}