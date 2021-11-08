package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.dto.EngineerListDto;
import nicomed.tms.projectplanner.entity.Engineer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {
        AddressMapper.class,
        RoleMapper.class,
        DepartmentMapper.class
})
public interface EngineerMapper {

    EngineerMapper INSTANCE = Mappers.getMapper(EngineerMapper.class);

    @Mapping(source = "role.name", target = "roleName")
    EngineerJavaDto mapToJavaDto(Engineer engineer);

    List<EngineerJavaDto> mapToListJavaDto(List<Engineer> engineers);

    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "address", target = "addressDto")
    EngineerListDto mapToEngineerListDto(Engineer engineer);

    List<EngineerListDto> mapToCollectionEngineerListDto(List<Engineer> engineers);
}