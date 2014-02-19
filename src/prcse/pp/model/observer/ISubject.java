package prcse.pp.model.observer;

/**
 * To be overridden in to allow Observer pattern to be implemnted.
 * @author PRCSE
 */
public interface ISubject {

    Boolean registerObserver(IObserver o);

    Boolean removeObserver(IObserver o);

    void notifyObservers();
}
