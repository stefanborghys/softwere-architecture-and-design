package be.seriousbusiness.pattern.behavioral.observer.simple.observer;

/**
 * Interested to be notified about a subject's change of state.
 */
public interface Observer {

    /**
     * Simple update method, called when the subject's state has changed.
     */
    void update();

}
