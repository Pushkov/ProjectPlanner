package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleDto1;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long> {

    List<Role> findAllByPermissions(Permission permission);

    List<RoleDto1> findAllJavaDto();

    List<RoleDto> findAllJaxbDto();
}
