package nicomed.tms.projectplanner.services.util;

public class MessageUtil {

    public static <T> String getNoEntityByIdFound(Class<T> clazz, Long id) {
        return clazz.getSimpleName() + " with id=" + id + " not found";
    }
}
