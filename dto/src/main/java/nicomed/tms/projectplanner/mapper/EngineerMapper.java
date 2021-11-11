package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.EngineerDto;
import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.entity.Engineer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {
        AddressMapper.class,
        RoleMapper.class,
        DepartmentMapper.class
})
public interface EngineerMapper {

    @Mapping(source = "role.name", target = "roleName")
    EngineerJavaDto mapToJavaDto(Engineer engineer);

    List<EngineerJavaDto> mapToListJavaDto(List<Engineer> engineers);

    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "address", target = "addressDto")
    EngineerDto mapToEngineerListDto(Engineer engineer);

    List<EngineerDto> mapToCollectionEngineerListDto(List<Engineer> engineers);
}
