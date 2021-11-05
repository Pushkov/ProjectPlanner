package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.mapper.PermissionMapper;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Permission, Long> implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAllByNameContains(String subName) {
        return permissionRepository.findAllByNameContainsIgnoreCase(subName);
    }

    @Override
    public List<Permission> findByRoles_Id(Long id) {
        return permissionRepository.findByRoles_Id(id);
    }

    @Override
    public List<PermissionDto> findAllJaxbDto() {
        return findAll().stream()
                .map(PermissionMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JpaRepository<Permission, Long> getRepository() {
        return permissionRepository;
    }
}
