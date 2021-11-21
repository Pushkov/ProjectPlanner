package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.mapper.PermissionMapper;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@JpaImpl
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper mapper;


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

    @Transactional
    @Override
    public void save(Long id, PermissionDto dto) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Permission with id=" + id + " not found"));
        permission.setName(dto.getName());
        permission.setComment(dto.getComment());
    }


    @Override
    public PermissionDto findById(Long id) {
        return permissionRepository.findById(id).map(mapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("Permission with id=" + id + " not found"));
    }

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

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
