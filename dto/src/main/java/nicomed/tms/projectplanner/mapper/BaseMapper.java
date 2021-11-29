package nicomed.tms.projectplanner.mapper;

public interface BaseMapper<D, T> {

    D mapToDto(T entity);

    T mapToEntity(D dto);
}
