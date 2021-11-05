package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleJavaDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import static nicomed.tms.projectplanner.mapper.RoleMapper.INSTANCE;

@RequiredArgsConstructor
@Service
public class RoleJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public Role findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        List<Permission> permissions = permissionRepository.findByRoles_Id(role.getId());
        role.setPermissions(permissions);
        return role;
    }

    @Override
    public RoleDto findJaxbDtoById(Long id) {
        return INSTANCE.mapToJaxbDto(findById(id));
    }

    @Override
    public RoleJavaDto findJavaDtoById(Long id) {
        return INSTANCE.mapToJavaDto(findById(id));
    }

    @Override
    public Collection<Role> findAll() {
        Collection<Role> roleCollection = roleRepository.findAll();
        for (Role role : roleCollection) {
            role.setPermissions(permissionRepository.findByRoles_Id(role.getId()));
        }
        return roleCollection;
    }

    @Override
    public List<Role> findAllByPermissions(Permission permission) {
        return roleRepository.findAllByPermissions(permission);
    }

    @Override
    public List<RoleJavaDto> findAllJavaDto() {
        return INSTANCE.mapToListJavaDto((List<Role>) findAll());
    }

    @Override
    public List<RoleDto> findAllJaxbDto() {
        return INSTANCE.mapToListJaxbDto((List<Role>) findAll());
    }
}
