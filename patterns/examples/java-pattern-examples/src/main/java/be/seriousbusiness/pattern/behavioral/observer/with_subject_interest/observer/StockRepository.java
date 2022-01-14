package be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.observer;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.Interest;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject.Subject;

public class StockRepository implements Observer {

    @Override
    public void update(Subject subject, Interest interest) {
        System.out.printf("> %s received update for interest [%s] in subject [%s]\n", getClass().getSimpleName(), interest, subject);
    }

    @Override
    public void deleted(Subject subject) {
        System.out.printf("> %s received deletion of subject [%s]\n", getClass().getSimpleName(), subject);
    }

}
