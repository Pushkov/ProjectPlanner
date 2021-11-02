package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.entity.Permission;

import java.util.List;

public interface PermissionService extends CrudService<Permission, Long> {

    Permission findByName(String name);

    List<Permission> findAllByNameContains(String subName);
}
