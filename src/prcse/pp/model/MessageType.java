package prcse.pp.model;

/**
 * Specify the nature of the message
 * @author PRCSE
 */
public enum MessageType {

    INBOX,
    MAINTENANCE,
    ALERT;

    @Override
    public String toString() {
        String result = "UNKNOWN";
        switch(this){
            case INBOX:
                result = "INBOX";
            break;
            case MAINTENANCE:
                result = "MAINTENANCE";
            break;
            case ALERT:
                result = "ALERT";
            break;
        }
        return result;
    }

}
