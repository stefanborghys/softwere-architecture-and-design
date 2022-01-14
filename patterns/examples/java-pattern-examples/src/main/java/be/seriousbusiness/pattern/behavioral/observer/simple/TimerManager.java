package be.seriousbusiness.pattern.behavioral.observer.simple;

import java.util.Optional;

import be.seriousbusiness.pattern.behavioral.observer.simple.subject.Timer;
import org.apache.commons.lang3.Validate;

public class TimerManager {

    private Timer timer;

    public void setTimer(Timer timer) {
        getTimer().ifPresent(currentTimer -> Validate.isTrue(!currentTimer.isStarted(), "The currently managed timer has already been started. Please stop it, before replacing it by another timer."));
        this.timer = timer;
    }

    public Optional<Timer> getTimer() {
        return Optional.ofNullable(timer);
    }
}
