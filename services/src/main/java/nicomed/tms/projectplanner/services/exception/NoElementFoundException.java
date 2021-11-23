package nicomed.tms.projectplanner.services.exception;

public class NoElementFoundException extends RuntimeException {

    public static final String EXCEPTION_MESSAGE_1 = "with id=";

    public static final String EXCEPTION_MESSAGE_2 = "not found";

//    public NoElementFoundException(String className, Long id) {
//        super(className + " " + EXCEPTION_MESSAGE_1 + id + " " + EXCEPTION_MESSAGE_2);
//    }
//
//    public NoElementFoundException(String className, Integer id) {
//        super(className + " " + EXCEPTION_MESSAGE_1 + id + " " + EXCEPTION_MESSAGE_2);
//    }

    public NoElementFoundException(String className, String id) {
        super(className + " " + EXCEPTION_MESSAGE_1 + id + " " + EXCEPTION_MESSAGE_2);
    }
}
