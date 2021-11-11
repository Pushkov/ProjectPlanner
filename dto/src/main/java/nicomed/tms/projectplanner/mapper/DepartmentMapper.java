package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DepartmentDtoSecond;
import nicomed.tms.projectplanner.dto.DepartmentJavaDto;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DepartmentMapper {

    @Mapping(target = "basicDep", source = "basicDepartment.name", defaultValue = " ")
    DepartmentDtoSecond mapToDtoSecond(Department department);

    //    /****************************************************************************************************
    Department mapJavaToEntity(DepartmentJavaDto dto);

    //    @Mapping(target = "basicDepartmentName", source = "basicDepartment.name", defaultValue = " ")
    DepartmentJavaDto mapToJavaDto(Department department);

    //     ****************************************************************************************************/
    List<DepartmentJavaDto> mapToListJavaDto(List<Department> departments);
}
