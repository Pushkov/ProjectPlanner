package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto mapToDto(Role role);

    List<RoleDto> mapToListDto(List<Role> roles);

    @Mapping(source = "permissionDto", target = "permissions")
    Role mapFullDtoToEntity(RoleFullDto dto);

    @Mapping(source = "permissions", target = "permissionDto")
    RoleFullDto mapToFullDto(Role role);

    List<RoleFullDto> mapToListFullDto(List<Role> roles);
}
