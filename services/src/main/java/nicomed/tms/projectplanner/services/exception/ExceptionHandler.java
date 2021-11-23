package nicomed.tms.projectplanner.services.exception;

import org.springframework.stereotype.Component;

import static java.lang.String.valueOf;

@Component
public class ExceptionHandler {

    public static NoElementFoundException throwNotFoundByIdException(String className, Object id) {
        return new NoElementFoundException(className, "id", valueOf(id));
    }

    public static NoElementFoundException throwNotFoundByNameException(String className, Object id) {
        return new NoElementFoundException(className, "name", valueOf(id));
    }
}
