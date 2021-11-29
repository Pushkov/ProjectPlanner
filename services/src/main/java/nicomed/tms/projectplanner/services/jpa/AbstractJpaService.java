package nicomed.tms.projectplanner.services.jpa;

import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.services.BaseJpaService;
import nicomed.tms.projectplanner.services.CrudService;

import java.util.Collection;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.throwNotFoundByIdException;

public abstract class AbstractJpaService<D, T extends BaseEntity<ID>, ID> implements BaseJpaService<T, ID>, CrudService<D, ID> {

    public T findEntityById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> throwNotFoundByIdException(getEntityClass(), id));
    }

    @Override
    public D findById(ID id) {
        return mapToDto(findEntityById(id));
    }

    @Override
    public void save(D dto) {
        getRepository().save(mapToEntity(dto));
    }

    @Override
    public Collection<D> findAll() {
        return getRepository().findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    public abstract D mapToDto(T entity);

    public abstract T mapToEntity(D dto);

    public abstract Class<T> getEntityClass();

}
