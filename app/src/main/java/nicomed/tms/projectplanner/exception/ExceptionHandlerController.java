package nicomed.tms.projectplanner.exception;

import nicomed.tms.projectplanner.services.exception.NoElementFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value
            = {NoSuchElementException.class,
            NoElementFoundException.class})
    protected ResponseEntity<Object> noSushElementConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "ERROR: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
