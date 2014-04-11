package prcse.pp.model;

import java.util.Date;

/**
 * Builds a Request object
 */
public class Request extends Message {

    private Property property;
    private Boolean  viewed;
    private String   tracking_id;
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
        super(tenant, message, date, id);
        this.status      = getStatus(status);
        this.viewed      = false;
        this.tracking_id = req_id;

        if(finished == null)
            this.finDate = null;
        else {
            this.finDate = finished;
        }
    }


    /**
     * Update the status of the message to seen
     */
    public MessageStatus getStatus(String status)
    {
        MessageStatus thisStatus = MessageStatus.RECEIVED;

        // Get the correct status
        switch(status){
            case "RECEIVED":
                thisStatus = MessageStatus.RECEIVED;
                break;
            case "IN_PROGRESS":
                thisStatus = MessageStatus.IN_PROGRESS;
                break;
            case "SCHEDULED":
                thisStatus = MessageStatus.SCHEDULED;
                break;
            case "SEEN":
                thisStatus = MessageStatus.SEEN;
                break;
            case "COMPLETED":
                thisStatus = MessageStatus.COMPLETED;
                break;
        }

        return thisStatus;
    }


}