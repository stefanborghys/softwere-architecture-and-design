package be.seriousbusiness.pattern.behavioral.observer.with_change_manager.observer;

import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.ApplicationNode;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.ApplicationNode.Status;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.Subject;

public class ApplicationStatusMonitor implements Observer{

    @Override
    public void update(Subject subject) {
        if(subject instanceof final ApplicationNode applicationNode){
            System.out.printf("All Application Nodes are %s", applicationNode.getStatus().name());
        }
    }
}
