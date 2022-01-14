package be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.observer;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.Interest;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject.Subject;

/**
 * Interested to be notified about a subject's change of state.
 */
public interface Observer {

    /**
     * Notifies that the subject's state has changed.
     *
     * @param subject the updated subject
     */
    void update(Subject subject, Interest interest);

    /**
     * Notifies that the subject is deleted.
     * To avoid dangling references at the observer's side.
     *
     * @param subject the deleted subject
     */
    void deleted(Subject subject);
}
