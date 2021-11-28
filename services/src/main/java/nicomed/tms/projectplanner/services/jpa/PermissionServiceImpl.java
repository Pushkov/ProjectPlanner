package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.mapper.PermissionMapper;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import nicomed.tms.projectplanner.services.RoleService;
import nicomed.tms.projectplanner.services.aspect.LoggegMethod;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import nicomed.tms.projectplanner.services.exception.ExceptionHandler;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@JpaImpl
public class PermissionServiceImpl implements PermissionService {

    private final RoleService roleService;
    private final PermissionRepository permissionRepository;
    private final PermissionMapper mapper;


    private Permission findEntityById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> ExceptionHandler.throwNotFoundByIdException(getEntityClass(), id));
    }

    @Override
    public List<PermissionDto> findAllDtoByNameContains(String subName) {
        return permissionRepository.findAllByNameContainsIgnoreCase(subName).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionDto> findAllDtoByRole_Id(Long id) {
        return permissionRepository.findByRoles_Id(id).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @LoggegMethod(value = "Permission id={id} updated", activity = "permission")
    @Transactional
    @Override
    public void save(Long id, PermissionDto dto) {
        Permission permission = findEntityById(id);
        permission.setName(dto.getName());
        permission.setComment(dto.getComment());
    }


    @Override
    public PermissionDto findById(Long id) {
        return mapper.mapToDto(findEntityById(id));
    }

    @LoggegMethod(value = "Permission {dto} saved", activity = "permission")
    @Override
    public void save(PermissionDto dto) {
        permissionRepository.save(mapper.mapToEntity(dto));
    }

    @Override
    public Collection<PermissionDto> findAll() {
        return permissionRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @LoggegMethod(value = "Permission id={id} deleted", activity = "permission")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(Long id) {
        Permission permission = findEntityById(id);
        permission.setRoles(null);
        removePermissionFromLinkedRoles(permission);
        permissionRepository.deleteById(id);
    }

    private Class<Permission> getEntityClass() {
        return Permission.class;
    }

    private List<Role> getRolesByPermission(Permission permission) {
        return roleService.findAllByPermissions(permission);
    }

    private void removePermissionFromLinkedRoles(Permission permission) {
        List<Role> roles = getRolesByPermission(permission);
        for (Role role : roles) {
            roleService.removePermission(role, permission);
        }
    }

    public List<PermissionDto> findAllByNotRole_Id(Long id) {
        List<Long> ids = permissionRepository.findByRoles_Id(id).stream()
                .map(Permission::getId)
                .collect(Collectors.toList());
        List<Permission> permissions = permissionRepository.findAll()
                .stream()
                .filter(x -> !ids.contains(x.getId()))
                .collect(Collectors.toList());

        return permissions.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

}
