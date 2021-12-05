package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Engineer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {
        AddressMapper.class,
        RoleMapper.class,
        DepartmentMapper.class
})
public interface EngineerMapper {

    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "address", target = "contactDetails")
    EngineerDto mapToDto(Engineer engineer);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "address", source = "contactDetails")
    @Mapping(target = "id", ignore = true)
    Engineer mapToEntity(EngineerDto dto);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "department.fullName", ignore = true)
    @Mapping(target = "address", source = "contactDetails")
    @Mapping(target = "id", ignore = true)
    void mapToEntity(@MappingTarget Engineer engineer, EngineerDto dto);

}
