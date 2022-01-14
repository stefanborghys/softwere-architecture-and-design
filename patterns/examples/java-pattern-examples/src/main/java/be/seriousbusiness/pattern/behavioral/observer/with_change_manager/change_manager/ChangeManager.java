package be.seriousbusiness.pattern.behavioral.observer.with_change_manager.change_manager;

import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.observer.Observer;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.Subject;

public interface ChangeManager {

    void register(Subject subject, Observer observer);

    void unregister(Subject subject, Observer observer);

    void update(Subject subject);

}
