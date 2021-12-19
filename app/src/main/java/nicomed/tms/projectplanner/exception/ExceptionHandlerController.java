package nicomed.tms.projectplanner.exception;

import nicomed.tms.projectplanner.security.jwt.JwtAuthenticationException;
import nicomed.tms.projectplanner.services.exception.NoElementFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value
            = {NoSuchElementException.class,
            NoElementFoundException.class})
    protected ResponseEntity<Object> noSuchElementConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "ERROR: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {UsernameNotFoundException.class,
            InternalAuthenticationServiceException.class
    })
    protected ResponseEntity<Object> badData(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Сheck the entered data";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = {JwtAuthenticationException.class
    })
    protected ResponseEntity<Object> invalidToken(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "JWT token is expired or invalid";

        System.out.println(" JWT BAD " + bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }


    @ExceptionHandler(value
            = {IllegalArgumentException.class})
    protected ResponseEntity<Object> incorrectEnumParseConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "CONVERTING ERROR: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {PdfGenerationErrorException.class})
    protected ResponseEntity<Object> pdfGenerationError(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "PDF GENERATION ERROR: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField().replace('.', '_');
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, status);
    }


}
