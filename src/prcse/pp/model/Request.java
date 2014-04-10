package prcse.pp.model;

import java.util.Date;

/**
 * Builds a Request object
 */
public class Request extends Message {

    private Property property;
    private Boolean  viewed;

    /**
     * New request constructor with default status
     */
    public Request(Tenant tenant, String message, Property p, Date date, int id)
    {
        super(tenant, message, date, id);
        this.status = MessageStatus.RECEIVED;
        this.viewed = false;
    }

    /**
     * Maintenance request constructor, allows a request to be made against a new
     * property
     * @param tenant the tenant making the request
     * @param message the message the tenant sends
     * @param p the property of which the request is being made against
     */
    public Request(Tenant tenant, String message, MessageStatus s, Property p, Date date, int id) {
        super(tenant, message, date, id);
        this.status       = s;
        this.type         = type.MAINTENANCE;
        this.property     = p;
        this.viewed       = false;
    }

    /**
     * Update the status of the message to seen
     */
    public Boolean messageSeen()
    {
        Boolean updated = false;
        if(this.viewed == false) {
            this.viewed = true;
            updated = true;
        }
        return updated;
    }
}