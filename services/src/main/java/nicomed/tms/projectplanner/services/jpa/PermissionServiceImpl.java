package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.enums.UserPermission;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

    @Override
    public List<Permission> findAll() {

        List<Permission> list = permissionRepository.findAll();
//        list.forEach(p -> p.setRoles(roleService.findAllByPermissionsExists(p)));
        return list;
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission findByName(String name) {
        UserPermission permission = UserPermission.valueOf(name);
        return permissionRepository.findByName(permission)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Permission> findAllByNameContains(String subName) {
        return permissionRepository.findAllByNameContainsIgnoreCase(subName);
    }

    @Override
    public List<Permission> findByRoles_Id(Long id) {
        return permissionRepository.findByRoles_Id(id);
    }
}
