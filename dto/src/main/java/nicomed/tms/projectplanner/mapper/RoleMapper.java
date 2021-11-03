package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.RoleDto1;
import nicomed.tms.projectplanner.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

//    Role mapToEntity(RoleDto dto);

    @Mapping(source = "role.permissions", target = "permissionsDtoList")
    RoleDto1 mapToDto(Role role);
}
