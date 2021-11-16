package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.mapper.PermissionMapper;
import nicomed.tms.projectplanner.repository.PermissionRepository;
import nicomed.tms.projectplanner.services.PermissionService;
import nicomed.tms.projectplanner.services.config.JpaImpl;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@JpaImpl
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper mapper;

    @Override
    public Permission findById(Long aLong) {
        return null;
    }

    @Override
    public void save(Permission entity) {
        permissionRepository.save(entity);
    }

    @Override
    public Collection<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public PermissionDto findDtoById(Long id) {
        return mapper.mapToDto(findById(id));
    }

    @Override
    public List<PermissionDto> findAllDto() {
        return mapper.mapToListDto((List<Permission>) findAll());
    }

    @Override
    public List<PermissionDto> findAllDtoByNameContains(String subName) {
        return mapper.mapToListDto(permissionRepository.findAllByNameContainsIgnoreCase(subName));
    }

    @Override
    public List<PermissionDto> findAllDtoByRole_Id(Long id) {
        return mapper.mapToListDto(permissionRepository.findByRoles_Id(id));
    }

    @Override
    public void save(PermissionDto dto) {
        save(mapper.mapToEntity(dto));
    }

    @Override
    public void save(Long id, PermissionDto dto) {
        Permission permission = findById(id);
        permission.setName(dto.getName());
    }
}
