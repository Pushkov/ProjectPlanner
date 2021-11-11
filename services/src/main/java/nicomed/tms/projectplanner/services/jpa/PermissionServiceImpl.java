package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.mapper.PermissionMapper;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper mapper;

    @Override
    public List<Permission> findAllByNameContains(String subName) {
        return permissionRepository.findAllByNameContainsIgnoreCase(subName);
    }

    @Override
    public List<Permission> findByRoles_Id(Long id) {
        return permissionRepository.findByRoles_Id(id);
    }

    @Override
    public List<PermissionDto> findAllDto() {
        return mapper.mapToListDto((List<Permission>) findAll());
    }

    @Override
    public List<PermissionDto> findAllDtoByNameContains(String subName) {
        return mapper.mapToListDto(findAllByNameContains(subName));
    }

    @Override
    public List<PermissionDto> findAllDtoByRole_Id(Long id) {
        return mapper.mapToListDto(findByRoles_Id(id));
    }


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
    public Collection<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
