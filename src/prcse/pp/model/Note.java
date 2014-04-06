package prcse.pp.model;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 02/04/14
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class Note extends Message {
    /**
     * Constructor for a note
     * @param the tenant who the note is being made for
     * @param the date of the message
     * @param message - content of message
     */
    public Note(Tenant tenant, String message, String date, int id)
    {
        super(message, date, id);
        this.type = MessageType.NOTE;
    }

    /**
     * Constructor for a note
     * @param the message for the note
     * @param the data of the message
     */
    public Note(String message, String date, int id) {
        super(message, date, id);
        this.type = MessageType.NOTE;
    }
}
