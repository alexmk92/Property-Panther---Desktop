package prcse.pp.model;

import java.io.Serializable;

/**
 * Set the state of of the Message ( only allowed to use this if type is Maint Request )
 * @author PRCSE
 */
public enum MessageStatus implements Serializable{

    RECEIVED,
    SEEN,
    SCHEDULED,
    IN_PROGRESS,
    COMPLETED;

    @Override
    public String toString() {
        String result = "UNKNOWN";
        switch(this){
            case RECEIVED:
                result = "RECEIVED";
            break;
            case SEEN:
                result = "SEEN";
            break;
            case SCHEDULED:
                result = "SCHEDULED";
            break;
            case IN_PROGRESS:
                result = "IN PROGRESS";
            break;
            case COMPLETED:
                result = "COMPLETED";
            break;
        }
        return result;
    }
}
