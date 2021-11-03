package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public void save(Role entity) {
        roleRepository.save(entity);
    }

    @Override
    public Collection<Role> findAll() {
        Collection<Role> roleCollection = roleRepository.findAll();
        return roleCollection;
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }


}
