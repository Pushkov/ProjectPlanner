package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.mapper.RoleMapper;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.services.RoleService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class RoleJpaServiceImpl extends AbstractJpaService<RoleDto, Role, Long> implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;
    private final PermissionRepository permissionRepository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public List<RoleDto> findDtoByExample(Role role) {
        return getRolesByExample(role).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleFullDto findFullDtoById(Long id) {
        return mapper.mapToFullDto(roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Role with id=" + id + " not found")));
    }

    @Override
    public List<RoleFullDto> findAllFullDto() {
        return roleRepository.findAll().stream()
                .map(mapper::mapToFullDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleFullDto> findFullDtoByExample(Role role) {
        return getRolesByExample(role).stream()
                .map(mapper::mapToFullDto)
                .collect(Collectors.toList());
    }

    private List<Role> getRolesByExample(Role role) {
        ExampleMatcher roleNameInsensitiveExMatcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Role> roleExample = Example.of(role, roleNameInsensitiveExMatcher);
        return roleRepository.findAll(roleExample);
    }

    @Override
    public void createRole(RoleFullDto dto) {
        roleRepository.save(mapper.mapFullDtoToEntity(dto));
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void saveFromDto(Long id, RoleFullDto dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Role with id=" + id + " not found"));
        role.setName(dto.getName());
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
    public RoleDto mapToDto(Role entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Role mapToEntity(RoleDto dto) {
        return mapper.mapToEntity(dto);
    }
}
