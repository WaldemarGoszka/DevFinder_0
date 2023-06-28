package pl.devfinder.business.management;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;


public class TokenExpirationTime {
    private static final int EXPIRATION_TIME = 10;

    public static OffsetDateTime getExpirationTime() {
        OffsetDateTime now = OffsetDateTime.now();
        return now.plus(EXPIRATION_TIME, ChronoUnit.MINUTES);
    }
}
