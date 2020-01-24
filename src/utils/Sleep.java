package utils;

import org.osbot.rs07.utility.ConditionalSleep;

import java.util.function.BooleanSupplier;

public final class Sleep {
    private Sleep() {
    }

    public static boolean sleepUntil(BooleanSupplier condition, int maxTime, int checkTime) {
        new ConditionalSleep(maxTime, checkTime) {
            @Override
            public boolean condition() throws InterruptedException {
                return condition.getAsBoolean();
            }
        }.sleep();
        return condition.getAsBoolean();
    }
}
