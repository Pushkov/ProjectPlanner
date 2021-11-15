package nicomed.tms.projectplanner.services.jpa;

import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.services.BaseJpaService;
import nicomed.tms.projectplanner.services.CrudService;

import java.util.Collection;
import java.util.NoSuchElementException;

public abstract class AbstractJpaService<T extends BaseEntity<ID>, ID> implements BaseJpaService<T,ID>, CrudService<T,ID> {

    @Override
    public T findById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new NoSuchElementException("not found"));

    }
    @Override
    public void save(T entity) {
        getRepository().save(entity);
    }

    @Override
    public Collection<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }


}
