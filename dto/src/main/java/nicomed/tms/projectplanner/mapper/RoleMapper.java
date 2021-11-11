package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleDto mapToDto(Role role);

    List<RoleDto> mapToListDto(List<Role> roles);

    @Mapping(source = "permissionDto", target = "permissions")
    Role mapFullDtoToEntity(RoleFullDto dto);

    @Mapping(source = "permissions", target = "permissionDto")
    RoleFullDto mapToFullDto(Role role);

    List<RoleFullDto> mapToListFullDto(List<Role> roles);
}
