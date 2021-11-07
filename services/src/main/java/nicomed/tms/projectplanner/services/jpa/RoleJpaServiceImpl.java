package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static nicomed.tms.projectplanner.mapper.RoleMapper.INSTANCE;

@RequiredArgsConstructor
@Service
public class RoleJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public RoleFullDto findFullDtoById(Long id) {
        return INSTANCE.mapToFullDto(findById(id));
    }

    @Override
    public RoleFullDto findFullDtoByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(NoSuchElementException::new);
        return INSTANCE.mapToFullDto(role);
    }

    @Override
    public RoleDto findDtoById(Long id) {
        return INSTANCE.mapToDto(findById(id));
    }

    @Override
    public List<RoleFullDto> findAllFullDto() {
        return INSTANCE.mapToListFullDto((List<Role>) findAll());
    }

    @Override
    public List<RoleDto> findAllDto() {
        return INSTANCE.mapToListDto((List<Role>) findAll());
    }
}
