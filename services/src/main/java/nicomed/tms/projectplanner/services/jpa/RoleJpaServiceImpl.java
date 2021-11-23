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
import java.util.NoSuchElementException;

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

    @Transactional
    @Override
    public RoleDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Transactional
    @Override
    public void save(RoleDto dto) {
        Role role = mapToEntity(dto);
        role.setPermissions(getPermissionsList(dto.getPermissions()));
        getRepository().save(role);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void saveFromDto(Long id, RoleDto dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Role with id=" + id + " not found"));
        role.setPermissions(getPermissionsList(dto.getPermissions()));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected List<Permission> getPermissionsList(List<PermissionDto> dtoList) {
        List<Permission> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoList.forEach(d -> result.addAll(permissionRepository.findAllByNameContainsIgnoreCase(d.getName())));
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
}
