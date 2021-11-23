package nicomed.tms.projectplanner.services.jpa;

import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.services.BaseJpaService;
import nicomed.tms.projectplanner.services.CrudDoubleDtoService;
import nicomed.tms.projectplanner.services.exception.NoElementFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

public abstract class AbstractDoubleDtoJpaService<F, D, T extends BaseEntity<ID>, ID> implements BaseJpaService<T, ID>, CrudDoubleDtoService<F, D, ID> {

    @Override
    public F findById(ID id) {
        return getRepository().findById(id).map(this::mapToDto)
                .orElseThrow(() -> new NoElementFoundException("for twin Element", valueOf(id)));

    }

    @Override
    public void save(F dto) {
        getRepository().save(mapToEntity(dto));
    }

    @Override
    public Collection<D> findAll() {
        return getRepository().findAll().stream()
                .map(this::mapToSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    public abstract D mapToSimpleDto(T entity);

    public abstract F mapToDto(T entity);

    public abstract T mapToEntity(F dto);


}
