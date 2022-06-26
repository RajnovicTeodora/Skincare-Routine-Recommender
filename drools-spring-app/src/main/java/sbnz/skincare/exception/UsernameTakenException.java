package sbnz.skincare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException() {
        super();
    }

    public UsernameTakenException(String message) {
        super(message);
    }

    public UsernameTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
