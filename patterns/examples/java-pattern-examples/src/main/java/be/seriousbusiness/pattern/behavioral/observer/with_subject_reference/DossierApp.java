package be.seriousbusiness.pattern.behavioral.observer.with_subject_reference;

import java.util.stream.LongStream;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.observer.AuditLogger;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.observer.Observer;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject.DefaultDossier;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject.Dossier;

public class DossierApp {

    public static void main(String... args) {
        final DossierRepository dossierRepository = new DossierRepository();
        final Observer auditLogger = new AuditLogger();

        LongStream.range(1, 101).mapToObj(DefaultDossier::new).forEach(defaultDossier -> {
            defaultDossier.attach(auditLogger);
            dossierRepository.add(defaultDossier);
        });

        dossierRepository.findById(1L).orElseThrow().approve();

        dossierRepository.remove(dossierRepository.findById(2L).orElseThrow());

        dossierRepository.getAll().forEach(Dossier::approve);
    }

}
