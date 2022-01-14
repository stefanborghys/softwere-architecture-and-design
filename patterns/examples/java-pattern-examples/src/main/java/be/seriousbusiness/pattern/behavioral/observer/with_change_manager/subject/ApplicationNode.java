package be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject;

public interface ApplicationNode extends Subject {

    enum Status {
        UP,
        DOWN
    }

    long getId();

    String getName();

    void setStatus(Status status);

    Status getStatus();

}
