package nicomed.tms.projectplanner.services.exception;

public class NoElementFoundException extends RuntimeException {

    public static final String EXCEPTION_MESSAGE_1 = "with ";

    public static final String EXCEPTION_MESSAGE_2 = "not found";

    public NoElementFoundException(String className, String field, String value) {
        super(
                new StringBuilder()
                        .append(className)
                        .append(" ")
                        .append(EXCEPTION_MESSAGE_1)
                        .append(field)
                        .append(" =")
                        .append(value)
                        .append(EXCEPTION_MESSAGE_2)
                        .toString()
        );
    }


}
