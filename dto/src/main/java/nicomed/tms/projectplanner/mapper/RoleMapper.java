package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.role.RoleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;
import nicomed.tms.projectplanner.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {


    RoleSimpleDto mapToSimpleDto(Role entity);

    @Mapping(target = "permissionsDto", source = "entity.permissions")
    RoleDto mapToDto(Role entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permissions", source = "dto.permissionsDto")
    Role mapToEntity(RoleDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permissions", source = "dto.permissionsDto")
    Role mapToEntity(@MappingTarget Role role, RoleDto dto);


}
