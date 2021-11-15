package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.mapper.RoleMapper;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.services.RoleService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@JpaImpl
public class RoleJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public RoleFullDto findFullDtoById(Long id) {
        return mapper.mapToFullDto(findById(id));
    }

    @Override
    public RoleFullDto findFullDtoByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(NoSuchElementException::new);
        return mapper.mapToFullDto(role);
    }

    @Override
    public RoleDto findDtoById(Long id) {
        return mapper.mapToDto(findById(id));
    }

    @Override
    public List<RoleFullDto> findAllFullDto() {
        return mapper.mapToListFullDto((List<Role>) findAll());
    }

    @Override
    public List<RoleDto> findAllDto() {
        return mapper.mapToListDto((List<Role>) findAll());
    }

    @Override
    public List<RoleDto> findRole(Role role) {
        ExampleMatcher roleNameInsensitiveExMatcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Role> roleExample = Example.of(role, roleNameInsensitiveExMatcher);
        return mapper.mapToListDto(roleRepository.findAll(roleExample));
    }
}
