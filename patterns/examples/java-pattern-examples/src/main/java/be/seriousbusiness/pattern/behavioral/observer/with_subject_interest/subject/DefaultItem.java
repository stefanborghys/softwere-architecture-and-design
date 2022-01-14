package be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject;

import java.util.Objects;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class DefaultItem implements Item{

    private final long id;
    private final String name;

    public DefaultItem(long id, String name) {
        this.id = id;
        this.name = Validate.notBlank(name, "Name cannot be blanc");
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultItem)) {
            return false;
        }
        DefaultItem that = (DefaultItem) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append("id", id).append("name", name).toString();
    }
}
