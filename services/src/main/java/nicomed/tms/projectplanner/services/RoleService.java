package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleJavaDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long> {

    RoleDto findJaxbDtoById(Long id);

    RoleJavaDto findJavaDtoById(Long id);

    List<Role> findAllByPermissions(Permission permission);

    List<RoleJavaDto> findAllJavaDto();

    List<RoleDto> findAllJaxbDto();
}
