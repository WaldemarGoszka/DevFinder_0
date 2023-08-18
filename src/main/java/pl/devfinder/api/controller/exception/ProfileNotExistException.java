package pl.devfinder.api.controller.exception;

public class ProfileNotExistException extends RuntimeException {
    public ProfileNotExistException(String message) {
        super(message);
    }
}
