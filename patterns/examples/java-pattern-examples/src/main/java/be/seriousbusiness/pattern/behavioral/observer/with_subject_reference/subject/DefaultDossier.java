package be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_reference.observer.Observer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class DefaultDossier implements Dossier {

    private final long id;
    private boolean approved;
    private final List<Observer> observers;

    public DefaultDossier(long id) {
        this.id = id;
        this.observers = new ArrayList<>();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public boolean isApproved() {
        return approved;
    }

    @Override
    public void approve() {
        if(!approved){
            this.approved = true;
            System.out.printf("> Dossier [%s] was approved\n", getId());
            notifyUpdate();
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyUpdate() {
        observers.forEach(observer -> observer.update(this));
    }

    @Override
    public void notifyDeleted() {
        observers.forEach(observer -> observer.deleted(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultDossier)) {
            return false;
        }
        DefaultDossier that = (DefaultDossier) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append("id", id).append("approved", approved).toString();
    }
}
