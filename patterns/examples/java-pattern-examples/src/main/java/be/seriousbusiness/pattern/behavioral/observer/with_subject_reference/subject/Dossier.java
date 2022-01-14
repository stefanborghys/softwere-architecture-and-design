package be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject;

public interface Dossier extends Subject {

    long getId();

    boolean isApproved();

    void approve();
}
