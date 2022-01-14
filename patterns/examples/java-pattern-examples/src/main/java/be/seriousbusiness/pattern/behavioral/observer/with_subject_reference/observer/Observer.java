package be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.observer;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject.Subject;

/**
 * Interested to be notified about a subject's change of state.
 */
public interface Observer {

    /**
     * Notifies that the subject's state has changed.
     *
     * @param subject the updated subject
     */
    void update(Subject subject);

    /**
     * Notifies that the subject is deleted.
     * To avoid dangling references at the observer's side.
     *
     * @param subject the deleted subject
     */
    void deleted(Subject subject);
}
