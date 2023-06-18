package pl.devfinder.business.management;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public final class Utility {

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

}
