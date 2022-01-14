package be.seriousbusiness.pattern.behavioral.observer.with_change_manager;

import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.observer.ApplicationStatusMonitor;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.ApplicationNode.Status;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject.DistributedApplication;

public class ApplicationStatusApp {

    public static void main(String... args) {
        ApplicationStatusMonitor applicationStatusMonitor = new ApplicationStatusMonitor();

        DistributedApplication distributedApplication1 = new DistributedApplication(1, "Application Node 1");
        distributedApplication1.attach(applicationStatusMonitor);

        DistributedApplication distributedApplication2 = new DistributedApplication(2, "Application Node 2");
        distributedApplication2.attach(applicationStatusMonitor);

        DistributedApplication distributedApplication3 = new DistributedApplication(3, "Application Node 3");
        distributedApplication3.attach(applicationStatusMonitor);

        distributedApplication1.setStatus(Status.UP);
        distributedApplication3.setStatus(Status.UP);
        distributedApplication2.setStatus(Status.UP);
    }

}
