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
     * @param message - content of message
     */
    public Note(Tenant tenant, String message)
    {
        super(message);
        this.type = MessageType.NOTE;
    }
}
