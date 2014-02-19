package prcse.pp.model.observer;

/**
 * Method to be overridden
 * @author PRCSE
 */
public interface IObserver {
    /**
     * Notifies the observer that an update has taken place
     * on its Subject.
     */
    void update();
}

