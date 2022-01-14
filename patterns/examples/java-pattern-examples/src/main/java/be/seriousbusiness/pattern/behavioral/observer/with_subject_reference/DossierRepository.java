package be.seriousbusiness.pattern.behavioral.observer.with_subject_reference;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject.Dossier;
import org.apache.commons.lang3.Validate;

import static java.util.Collections.unmodifiableSet;

public class DossierRepository {

    private Set<Dossier> dossiers;

    public DossierRepository() {
        this.dossiers = new HashSet<>();
    }

    public void add(Dossier dossier){
        Validate.notNull(dossier, "A dossier is required");
        dossiers.add(dossier);
    }

    public void remove(Dossier dossier){
        if(dossiers.remove(dossier)){
            dossier.notifyDeleted();
        }
    }

    public Set<Dossier> getAll(){
        return unmodifiableSet(dossiers);
    }

    public Optional<Dossier> findById(long id){
        return dossiers.stream().filter(dossier -> dossier.getId() == id).findFirst();
    }

}
