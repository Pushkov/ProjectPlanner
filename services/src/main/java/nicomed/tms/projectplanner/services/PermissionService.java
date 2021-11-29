package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.PermissionDto;

import java.util.List;

public interface PermissionService extends CrudService<PermissionDto, Long> {

    default List<PermissionDto> findAllDtoByNameContains(String subName) {
        throw new UnsupportedOperationException();
    }

    default List<PermissionDto> findAllDtoByRole_Id(Long id) {
        throw new UnsupportedOperationException();
    }

    void save(Long id, PermissionDto dto);

}
