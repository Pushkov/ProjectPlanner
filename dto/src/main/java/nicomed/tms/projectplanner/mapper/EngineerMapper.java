package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Engineer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {
        AddressMapper.class,
        RoleMapper.class,
        DepartmentMapper.class
})
public interface EngineerMapper {

    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "address", target = "contactDetails")
    EngineerDto mapToDto(Engineer engineer);

    Engineer mapToEntity(EngineerDto dto);
}
