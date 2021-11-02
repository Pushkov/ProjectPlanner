package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.enums.UserPermission;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

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
        return permissionRepository.findAll();
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

        return permissionRepository.findAllByName(subName);
    }
}
