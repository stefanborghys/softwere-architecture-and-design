package be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject;

import java.util.Set;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.Interest;

public interface ShoppingCart extends Subject {

    enum ShoppingCartInterest implements Interest {
        ITEM_ADDED,
        ITEM_REMOVED,
        ITEMS_PAID
    }

    long getId();

    void add(Item item);

    void remove(Item item);

    Set<Item> getItems();

    void paid();

}
