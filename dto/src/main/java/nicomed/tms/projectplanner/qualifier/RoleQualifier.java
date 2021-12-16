package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.role.RoleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.repository.RoleRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleQualifier extends AbstractEntityQualifier<Role, Long> {

    private final RoleRepository repository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return repository;
    }

    public Role getBy(RoleDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Role getBySimple(RoleSimpleDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("roleById")
    public Role getById(Long id) {
        return getEntity(id);
    }

}

