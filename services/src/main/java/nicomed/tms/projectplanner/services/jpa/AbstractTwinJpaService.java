package nicomed.tms.projectplanner.services.jpa;

import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.services.BaseJpaService;
import nicomed.tms.projectplanner.services.CrudTwinService;
import nicomed.tms.projectplanner.services.exception.NoElementFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractTwinJpaService<C, D, T extends BaseEntity<ID>, ID> implements BaseJpaService<T, ID>, CrudTwinService<C, D, ID> {

    @Override
    public C findById(ID id) {
        return getRepository().findById(id).map(this::mapToFullDto)
                .orElseThrow(() -> new NoElementFoundException(T.class.getSimpleName(), id)));

    }

    @Override
    public void save(C dto) {
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

    public abstract C mapToFullDto(T entity);

    public abstract T mapToEntity(C dto);


}
