package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long> {

    List<Role> findAllByPermissions(Permission permission);
}
