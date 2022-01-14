package be.seriousbusiness.pattern.behavioral.observer.simple.observer;

public class TickCounter implements Observer{

    private long numberOfTicks;

    @Override
    public void update() {
        System.out.printf("> %s received update\n", getClass().getSimpleName());
        numberOfTicks++;

        System.out.println("Tick: " + numberOfTicks);
    }
}
