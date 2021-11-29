package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.role.RoleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;

import java.util.List;

public interface RoleService extends CrudDoubleDtoService<RoleDto, RoleSimpleDto, Long> {

    void saveFromDto(Long id, RoleDto dto);

    List<Role> findAllByPermissions(Permission permission);

    void addPermission(Role role, Permission permission);

    void addPermissionById(Long roleId, Long permissionId);

    void removePermission(Role role, Permission permission);

    void removePermissionById(Long roleId, Long permissionId);
}
