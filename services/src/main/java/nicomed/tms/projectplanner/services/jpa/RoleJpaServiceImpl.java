package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.dto.role.RoleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.mapper.RoleMapper;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.services.RoleService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.throwNotFoundByIdException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class RoleJpaServiceImpl extends AbstractDoubleDtoJpaService<RoleDto, RoleSimpleDto, Role, Long> implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;
    private final PermissionRepository permissionRepository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public RoleDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Transactional
    @Override
    public void save(RoleDto dto) {
        Role role = mapToEntity(dto);
        role.setPermissions(getPermissionsList(dto.getPermissionsDto()));
        getRepository().save(role);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void saveFromDto(Long id, RoleDto dto) {
        Role role = findEntityById(id);
        mapper.mapToEntity(role, dto);
        role.setPermissions(getPermissionsList(dto.getPermissionsDto()));
    }

    @Override
    public void delete(Long id) {
        Role role = findEntityById(id);
        role.setPermissions(null);
        super.delete(id);
    }


    protected List<Permission> getPermissionsList(List<PermissionDto> dtoList) {
        List<Permission> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoList.forEach(d -> result.add(permissionRepository.findById(d.getId())
                    .orElseThrow(() -> throwNotFoundByIdException(Permission.class, d.getId()))));
        }
        return result;

    }

    @Override
    public RoleSimpleDto mapToSimpleDto(Role entity) {
        return mapper.mapToSimpleDto(entity);
    }

    @Override
    public RoleDto mapToDto(Role entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Role mapToEntity(RoleDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public List<Role> findAllByPermissions(Permission permission) {
        return roleRepository.findAllByPermissions(permission);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addPermission(Role role, Permission permission) {
        //ToDo permission validation ??????????
        role.getPermissions().add(permission);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void removePermission(Role role, Permission permission) {
        //ToDo permission validation ??????????
        role.getPermissions().remove(permission);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addPermissionById(Long roleId, Long permissionId) {
        addPermission(
                findEntityById(roleId),
                permissionRepository.findById(permissionId)
                        .orElseThrow(() -> throwNotFoundByIdException(Permission.class, permissionId))
        );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void removePermissionById(Long roleId, Long permissionId) {
        removePermission(
                findEntityById(roleId),
                permissionRepository.findById(permissionId)
                        .orElseThrow(() -> throwNotFoundByIdException(Permission.class, permissionId))
        );
    }
}
