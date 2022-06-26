package sbnz.skincare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EmailTakenException extends RuntimeException {

    public EmailTakenException() {
        super();
    }

    public EmailTakenException(String message) {
        super(message);
    }

    public EmailTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
