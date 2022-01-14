package be.seriousbusiness.pattern.behavioral.observer.with_change_manager.subject;

import java.util.Objects;

import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.change_manager.ChangeManager;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.change_manager.AllStatussesUpChangeManager;
import be.seriousbusiness.pattern.behavioral.observer.with_change_manager.observer.Observer;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DistributedApplication implements ApplicationNode {

    private final ChangeManager changeManager;
    private Status status;
    private final long id;
    private final String name;

    public DistributedApplication(ChangeManager changeManager, long id, String name) {
        this.changeManager = changeManager;
        this.status = Status.DOWN;
        this.id = id;
        this.name = Validate.notBlank(name, "Name cannot be blank");
    }

    public DistributedApplication(long id, String name) {
        this(AllStatussesUpChangeManager.getInstance(), id, name);
    }

    @Override
    public void attach(Observer observer) {
        changeManager.register(this, observer);
    }

    @Override
    public void detach(Observer observer) {
        changeManager.register(this, observer);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setStatus(Status status) {
        Validate.notNull(status, "Status is required");
        this.status = status;
        if(status == Status.UP){
            System.out.printf("> %s is UP!\n", this);

            changeManager.update(this);
        }
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DistributedApplication)) {
            return false;
        }
        DistributedApplication that = (DistributedApplication) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("status", status).append("id", id).append("name", name).toString();
    }
}
