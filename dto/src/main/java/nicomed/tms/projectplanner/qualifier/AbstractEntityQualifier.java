package nicomed.tms.projectplanner.qualifier;

import java.util.NoSuchElementException;

public abstract class AbstractEntityQualifier<T, ID> implements QualifierService<T, ID> {

    protected T getEntity(ID id) {
        if (id == null) {
            return null;
        }
        return getRepository().findById(id).orElseThrow(() -> new NoSuchElementException(""));
    }


}
