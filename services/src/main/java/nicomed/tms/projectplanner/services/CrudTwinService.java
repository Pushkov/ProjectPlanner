package nicomed.tms.projectplanner.services;

import java.util.Collection;

public interface CrudTwinService<C, D, ID> {

    C findById(ID id);

    void save(C entity);

    Collection<D> findAll();

    void delete(ID id);
}
