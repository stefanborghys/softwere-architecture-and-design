package be.seriousbusiness.pattern.behavioral.observer.simple.observer;

import java.time.LocalTime;

import be.seriousbusiness.pattern.behavioral.observer.simple.TimerManager;
import be.seriousbusiness.pattern.behavioral.observer.simple.subject.Timer;
import org.apache.commons.lang3.Validate;

import static java.time.format.DateTimeFormatter.ISO_TIME;

public class TimeVisualiser implements Observer{

    private TimerManager timerManager;

    public TimeVisualiser(TimerManager timerManager) {
        this.timerManager = Validate.notNull(timerManager, "A timer manager is required");
    }

    @Override
    public void update() {
        System.out.printf("> %s received update\n", getClass().getSimpleName());

        Timer timer = timerManager.getTimer().orElseThrow(() -> new IllegalStateException("Received an update but no timer is available!"));

        System.out.printf("[%s]\n", ISO_TIME.format(LocalTime.ofNanoOfDay(timer.getDuration().toNanos())));
    }


}
