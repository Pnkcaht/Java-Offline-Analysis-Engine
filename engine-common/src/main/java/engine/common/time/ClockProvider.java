package engine.common.time;

import java.time.Clock;

public final class ClockProvider {

    private static Clock clock = Clock.systemUTC();

    private ClockProvider() {}

    public static Clock clock() {
        return clock;
    }

    public static void override(Clock newClock) {
        clock = newClock;
    }
}
