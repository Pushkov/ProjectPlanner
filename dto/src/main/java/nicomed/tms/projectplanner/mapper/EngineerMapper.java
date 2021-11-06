package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.entity.Engineer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {
        AddressMapper.class,
        RoleMapper.class
})
public interface EngineerMapper {

    EngineerMapper INSTANCE = Mappers.getMapper(EngineerMapper.class);

    @Mapping(source = "role.name", target = "roleName")
    EngineerJavaDto mapToJavaDto(Engineer engineer);

//    @Mapping(source = "role.permissions", target = "permissionDto")
//    RoleDto mapToJaxbDto(Role role);

    List<EngineerJavaDto> mapToListJavaDto(List<Engineer> engineers);

//    List<RoleDto> mapToListJaxbDto(List<Role> roles);
}
