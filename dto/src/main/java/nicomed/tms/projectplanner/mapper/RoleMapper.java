package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.role.RoleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;
import nicomed.tms.projectplanner.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {


    RoleSimpleDto mapToSimpleDto(Role entity);

    RoleDto mapToDto(Role entity);

    Role mapToEntity(RoleDto dto);

    Role mapToEntity(@MappingTarget Role role, RoleDto dto);


}
