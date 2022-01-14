package be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.Interest;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.observer.Observer;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableSet;

public class DefaultShoppingCart implements ShoppingCart {

    private final long id;
    private final Map<Interest, List<Observer>> observersByInterest;
    private final Set<Item> items;
    private boolean paid;

    public DefaultShoppingCart(long id) {
        this.id = id;
        this.observersByInterest = new HashMap<>();
        this.items = new HashSet<>();
        this.paid = false;
    }

    @Override
    public long getId() {
        return id;
    }


    @Override
    public void attach(Observer observer, Interest interest) {
        Validate.notNull(observer,"An observer is required");
        Validate.notNull(interest,"An interest is required");

        List<Observer> observers = observersByInterest.putIfAbsent(interest, asList(observer));
        if(observers!=null) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer, Interest interest) {
        Validate.notNull(observer,"An observer is required");
        Validate.notNull(interest,"An interest is required");

        observersByInterest.computeIfPresent(interest,(currentInterest,currentObservers) -> {
            ArrayList<Observer> updatedObservers = new ArrayList<>(currentObservers);
            updatedObservers.remove(observer);
            return updatedObservers;
        });
    }

    @Override
    public void notifyUpdate(Interest interest) {
        Validate.notNull(interest,"An interest is required");

        observersByInterest.getOrDefault(interest, emptyList()).forEach(observer -> observer.update(this, interest));
    }

    @Override
    public void notifyDeleted() {
        observersByInterest.values().stream().flatMap(List::stream).forEach(observer -> observer.deleted(this));
    }

    @Override
    public void add(Item item) {
        Validate.notNull(item,"An item is required");
        if(items.add(item)) {
            notifyUpdate(ShoppingCartInterest.ITEM_ADDED);
        }
    }

    @Override
    public void remove(Item item) {
        Validate.notNull(item,"An item is required");
        if(items.remove(item)){
            notifyUpdate(ShoppingCartInterest.ITEM_REMOVED);
        }
    }

    @Override
    public Set<Item> getItems() {
        return unmodifiableSet(items);
    }

    @Override
    public void paid() {
        if(!paid){
            this.paid = true;
            notifyUpdate(ShoppingCartInterest.ITEMS_PAID);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultShoppingCart)) {
            return false;
        }
        DefaultShoppingCart that = (DefaultShoppingCart) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("items", items).toString();
    }
}
