package pl.devfinder.business.management;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@UtilityClass
public final class Utility {

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

    public static String encodePassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

}
