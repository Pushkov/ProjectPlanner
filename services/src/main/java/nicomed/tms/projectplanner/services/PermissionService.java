package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.dto.PermissionJavaDto;
import nicomed.tms.projectplanner.entity.Permission;

import java.util.List;

public interface PermissionService extends CrudService<Permission, Long> {

    List<Permission> findAllByNameContains(String subName);

    List<Permission> findByRoles_Id(Long id);

    List<PermissionDto> findAllJaxbDto();

    List<PermissionJavaDto> findAllJavaDto();

    PermissionDto findPermissionJaxbDtoById(Long id);
}
