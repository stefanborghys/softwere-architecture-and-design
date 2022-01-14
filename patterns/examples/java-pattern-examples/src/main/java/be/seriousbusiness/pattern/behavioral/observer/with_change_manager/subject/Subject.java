package be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject;

import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.observer.Observer;

public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

}
