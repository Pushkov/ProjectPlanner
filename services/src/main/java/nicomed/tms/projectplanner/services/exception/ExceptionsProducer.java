package nicomed.tms.projectplanner.services.exception;

import nicomed.tms.projectplanner.converter.ExceptionTestService;
import org.springframework.stereotype.Component;

import static java.lang.String.valueOf;

@Component
public class ExceptionsProducer implements ExceptionTestService {

    public static <T> NoElementFoundException throwNotFoundByIdException(Class<T> clazz, Object id) {
        return new NoElementFoundException(clazz.getSimpleName(), "id", valueOf(id));
    }

    public static <T> NoElementFoundException throwNotFoundByNameException(Class<T> clazz, Object id) {
        return new NoElementFoundException(clazz.getSimpleName(), "name", valueOf(id));
    }

    public static <T> IncorrectEnumConvertingException throwIncorrectEnumConvertingException(Class<T> clazz) {
        return new IncorrectEnumConvertingException(clazz.getSimpleName());
    }

    public static <T> FormatConvertingException throwFormatConvertingException(Class<T> clazz) {
        return new FormatConvertingException("Converting " + clazz.getSimpleName() + " class error");
    }

    @Override
    public <T> void throwTestFormatConvertingException(Class<T> clazz) {
        throw throwFormatConvertingException(clazz);
    }

    public static IncorrectPageAssignmentException trowIncorrectPageAssignmentException(String value) {
        return new IncorrectPageAssignmentException(value);
    }
}
