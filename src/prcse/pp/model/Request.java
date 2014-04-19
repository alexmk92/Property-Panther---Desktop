package prcse.pp.model;

import java.util.Date;

/**
 * Builds a Request object
 */
public class Request extends Message {

    private Property property;
    private Boolean  viewed;
    private Date     finDate;


    /**
     * New request constructor with default status
     * @param tenant  - the tenant who made the request
     * @param message - the request details
     * @param date    - the log date
     * @param id      - the request id
     * @param status  - the requests current status
     * @param req_id  - the requests tracking id
     */
    public Request(Tenant tenant, String message, Date date, int id, String status, String req_id, Date finished)
    {
        super(tenant, message, date, id, status, req_id);
        this.viewed      = false;

        if(finished == null)
            this.finDate = null;
        else {
            this.finDate = finished;
        }
    }
}