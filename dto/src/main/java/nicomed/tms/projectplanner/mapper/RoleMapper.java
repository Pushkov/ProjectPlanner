package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleJavaDto;
import nicomed.tms.projectplanner.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

//    @Mapping(source = "role.permissions", target = "permissions")
    RoleJavaDto mapToJavaDto(Role role);

    @Mapping(source = "role.permissions", target = "permissionDto")
    RoleDto mapToJaxbDto(Role role);

    List<RoleJavaDto> mapToListJavaDto(List<Role> roles);

    List<RoleDto> mapToListJaxbDto(List<Role> roles);
}
