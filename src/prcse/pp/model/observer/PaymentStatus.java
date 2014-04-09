package prcse.pp.model.observer;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 09/04/14
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public enum PaymentStatus {

    PAID,
    PAIDLATE,
    PENDING,
    OVERDUE;

    @Override
    public String toString() {
        String result = "UNKNOWN";
        switch(this){
            case PAID:
                result = "PAID";
                break;
            case PAIDLATE:
                result = "PAID LATE";
                break;
            case PENDING:
                result = "PENDING";
                break;
            case OVERDUE:
                result = "OVERDUE";
                break;
        }
        return result;
    }
}
