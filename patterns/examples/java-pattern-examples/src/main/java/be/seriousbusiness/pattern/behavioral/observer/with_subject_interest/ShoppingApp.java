package be.seriousbusiness.pattern.behavioral.observer.with_subject_interest;

import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.observer.AccountingRepository;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.observer.Observer;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.observer.StockRepository;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject.DefaultItem;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject.DefaultShoppingCart;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject.Item;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject.ShoppingCart;
import be.seriousbusiness.pattern.behavioral.observer.with_subject_interest.subject.ShoppingCart.ShoppingCartInterest;

public class ShoppingApp {

    public static void main(String... args) {

        Observer accountingRepository = new AccountingRepository();
        Observer stockRepository = new StockRepository();

        final Item bananas = new DefaultItem(1, "Bananas");
        final Item rice = new DefaultItem(2,"Rice");
        final Item brocoli = new DefaultItem(3,"Brocoli");
        final Item chewingGum = new DefaultItem(4,"Chewing Gum");
        final Item wine = new DefaultItem(5,"Wine");

        final ShoppingCart shoppingCart = new DefaultShoppingCart(1L);
        shoppingCart.attach(accountingRepository, ShoppingCartInterest.ITEMS_PAID);
        shoppingCart.attach(stockRepository, ShoppingCartInterest.ITEM_ADDED);
        shoppingCart.attach(stockRepository, ShoppingCartInterest.ITEM_REMOVED);

        shoppingCart.add(bananas);
        shoppingCart.add(rice);
        shoppingCart.add(brocoli);
        shoppingCart.add(chewingGum);
        shoppingCart.add(wine);

        shoppingCart.remove(chewingGum);

        shoppingCart.paid();

        shoppingCart.detach(accountingRepository, ShoppingCartInterest.ITEMS_PAID);
        shoppingCart.detach(stockRepository, ShoppingCartInterest.ITEM_ADDED);
        shoppingCart.detach(stockRepository, ShoppingCartInterest.ITEM_REMOVED);


    }

}
