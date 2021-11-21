package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleDto mapToDto(Role role);

    Role mapToEntity(RoleDto roleDto);

    RoleFullDto mapToFullDto(Role role);

    Role mapFullDtoToEntity(RoleFullDto dto);

}
