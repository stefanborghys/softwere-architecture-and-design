package be.seriousbusiness.pattern.behavioral.observer.simple;

import java.time.Duration;
import java.util.TimerTask;

import be.seriousbusiness.pattern.behavioral.observer.simple.observer.Observer;
import be.seriousbusiness.pattern.behavioral.observer.simple.observer.TickCounter;
import be.seriousbusiness.pattern.behavioral.observer.simple.observer.TimeVisualiser;
import be.seriousbusiness.pattern.behavioral.observer.simple.subject.Timer;

public class TimerApp {

    public static void main(String... args) {
        final TimerManager timerManager = new TimerManager();

        final Timer timer = new Timer();
        timerManager.setTimer(timer);

        final Observer tickCounter = new TickCounter();
        timer.attach(tickCounter);

        final Observer timeVisualiser = new TimeVisualiser(timerManager);
        timer.attach(timeVisualiser);

        final java.util.Timer stopTimer = new java.util.Timer("stop");
        stopTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.stop();
                timer.detach(tickCounter);
                timer.detach(timeVisualiser);
                System.exit(0);
            }
        }, Duration.ofMinutes(2).toMillis());

        timer.start();
    }

}
