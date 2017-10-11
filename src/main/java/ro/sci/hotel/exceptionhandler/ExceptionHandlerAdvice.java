package ro.sci.hotel.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

/**
 * Error handling class
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleException(ValidationException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Room is booked OR wrong period is given; Please click Back in your browser");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("NullPointerException" + e.getMessage());
    }
}
