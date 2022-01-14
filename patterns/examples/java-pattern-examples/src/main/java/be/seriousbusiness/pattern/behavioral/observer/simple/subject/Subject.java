package be.seriousbusiness.pattern.behavioral.observer.simple.subject;

import be.seriousbusiness.pattern.behavioral.observer.simple.observer.Observer;

/**
 * The 'subject' to observe.
 * Provides a way to attach or detach multiple observers.
 */
public interface Subject {

    /**
     * Attach an observer to this subject.
     *
     * @param observer
     */
    void attach(Observer observer);

    /**
     * Detach an observer from this subject.
     *
     * @param observer
     */
    void detach(Observer observer);

    /**
     * Notifies all attached observers about a change of this subject's state.
     */
    void notifyUpdate();

}
