package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.dto.PermissionJavaDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import static nicomed.tms.projectplanner.mapper.PermissionMapper.INSTANCE;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

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
        return INSTANCE.mappingToListJaxbDto((List<Permission>) findAll());
    }

    @Override
    public List<PermissionJavaDto> findAllJavaDto() {
        return INSTANCE.mappingToListJavaDto((List<Permission>) findAll());
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public PermissionDto findPermissionJaxbDtoById(Long id) {
        return INSTANCE.mapToJaxbDto(findById(id));
    }

    @Override
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

    @Override
    public Collection<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
