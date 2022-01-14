package be.seriousbusiness.pattern.behavioral.observer.simple.subject;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import be.seriousbusiness.pattern.behavioral.observer.simple.observer.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.invoke.MethodHandles.lookup;

public class Timer implements Subject{

    private static final Logger LOGGER = LoggerFactory.getLogger(lookup().lookupClass());

    private final Set<Observer> observers;
    private boolean started;
    private long numberOfSeconds;

    public Timer() {
        this.observers = new HashSet<>();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void start(){
        if(!started) {
            started = true;
            System.out.println("> Timer started ...");
        }

        while (started) {
            try {
                Thread.sleep(1000);
                numberOfSeconds++;
                notifyUpdate();
            } catch (InterruptedException e) {
                LOGGER.warn("Thread sleep got interrupted!", e);
            }
        }
    }

    public void stop(){
        if(started) {
            started = false;
            System.out.println("> Timer stopped ...");
        }
    }

    public boolean isStarted() {
        return started;
    }

    public Duration getDuration(){
        return Duration.ofSeconds(numberOfSeconds);
    }

    @Override
    public void notifyUpdate() {
        observers.forEach(Observer::update);
    }
}
