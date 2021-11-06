package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DepartmentDtoSecond;
import nicomed.tms.projectplanner.dto.DepartmentJavaDto;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);


    @Mapping(target = "basicDep", source = "basicDepartment.name", defaultValue = " ")
    DepartmentDtoSecond mapToDtoSecond(Department department);

    //    /****************************************************************************************************
    Department mapJavaToEntity(DepartmentJavaDto dto);

    //    @Mapping(target = "basicDepartmentName", source = "basicDepartment.name", defaultValue = " ")
    DepartmentJavaDto mapToJavaDto(Department department);

    //     ****************************************************************************************************/
    List<DepartmentJavaDto> mapToListJavaDto(List<Department> departments);
}
