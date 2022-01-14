package be.seriousbusiness.pattern.behavioral.observer.with_change_manager.change_manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.observer.Observer;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.ApplicationNode;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.ApplicationNode.Status;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.Subject;
import org.apache.commons.lang3.Validate;

import static java.util.Arrays.asList;

/**
 * Singleton.
 */
public class AllStatussesUpChangeManager implements ChangeManager {
    private static AllStatussesUpChangeManager instance;
    private Map<Subject, List<Observer>> observersBySubject;

    private AllStatussesUpChangeManager() {
        observersBySubject = new HashMap<>();
    }

    @Override
    public void register(Subject subject, Observer observer) {
        Validate.notNull(subject,"A subject is required");
        Validate.notNull(observer, "An observer is required");

        List<Observer> observers = observersBySubject.putIfAbsent(subject, asList(observer));
        if(observers!=null) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(Subject subject, Observer observer) {
        Validate.notNull(subject,"A subject is required");
        Validate.notNull(observer, "An observer is required");

        observersBySubject.computeIfPresent(subject,(currentInterest,currentObservers) -> {
            ArrayList<Observer> updatedObservers = new ArrayList<>(currentObservers);
            updatedObservers.remove(observer);
            return updatedObservers;
        });
    }


    @Override
    public void update(Subject subject) {
        if(subject instanceof final ApplicationNode applicationNode){
            if(applicationNode.getStatus() == Status.UP){
                final List<Observer> observers = observersBySubject.get(subject);

                observers.forEach(observer -> {
                    boolean allOtherObservingApplicationNodesUp = observersBySubject.entrySet().stream()
                                      .filter(entry -> !entry.getKey().equals(subject))
                            .allMatch( entry -> {
                                final Subject otherSubject = entry.getKey();
                                final ApplicationNode otherApplicationNode = (ApplicationNode) otherSubject;
                                final List<Observer> otherSubjectObservers = entry.getValue();

                                if(otherSubjectObservers.contains(observer)) {
                                    return otherApplicationNode.getStatus() == Status.UP;
                                } else {
                                    return true;
                                }
                            });
                    if(allOtherObservingApplicationNodesUp){
                        observer.update(subject);
                    }
                });

            }
        }

    }

    /**
     * Manages the creation of a single instance of DefaultChangeManager.
     * Always returning the same instance.
     *
     * @return DefaultChangeManager
     */
    public static AllStatussesUpChangeManager getInstance(){
        if(instance == null){
            instance = new AllStatussesUpChangeManager();
        }
        return instance;
    }
}
