package be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.observer;

import java.time.format.DateTimeFormatter;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject.Dossier;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject.Subject;

import static java.time.LocalDateTime.now;

public class AuditLogger implements Observer {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss SSS");

    @Override
    public void update(Subject subject) {
        System.out.printf("> %s received update for subject [%s]\n", getClass().getSimpleName(), subject);

        if (subject instanceof Dossier dossier) {
            System.out.printf("Dossier with id: %s, was approved on %s\n", dossier.getId(), now().format(TIME_FORMAT));
        }
    }

    @Override
    public void deleted(Subject subject) {
        System.out.printf("> %s received deletion of subject [%s]\n", getClass().getSimpleName(), subject);
    }
}
