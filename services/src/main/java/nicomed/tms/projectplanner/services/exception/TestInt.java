package nicomed.tms.projectplanner.services.exception;

import org.springframework.stereotype.Service;

@Service
public interface TestInt<T> {

    default String getMsg(Class<T> clazz) {
        return clazz.getSimpleName();
    }
}
