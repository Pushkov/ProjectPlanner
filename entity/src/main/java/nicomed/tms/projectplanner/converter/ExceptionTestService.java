package nicomed.tms.projectplanner.converter;

public interface ExceptionTestService {

    <T> void throwTestFormatConvertingException(Class<T> clazz);
}
