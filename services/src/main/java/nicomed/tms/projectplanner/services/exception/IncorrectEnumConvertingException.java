package nicomed.tms.projectplanner.services.exception;

public class IncorrectEnumConvertingException extends RuntimeException {

    public IncorrectEnumConvertingException(String className) {
        super("Incorrect " + className + " convertation");
    }
}
