package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;

import java.util.List;

public interface PermissionService extends CrudService<Permission, Long> {

    PermissionDto findDtoById(Long id);

    List<PermissionDto> findAllDto();

    List<PermissionDto> findAllDtoByNameContains(String subName);

    default List<PermissionDto> findAllDtoByRole_Id(Long id) {
        throw new UnsupportedOperationException();
    }

    void save(PermissionDto dto);

    void save(Long id, PermissionDto dto);
}
