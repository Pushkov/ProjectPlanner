package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DepartmentMapper {

    @Mapping(target = "basicDep", source = "basicDepartment.name", defaultValue = " ")
    DepartmentDto mapToDto(Department department);

    @Named("departmentSimple")
    DepartmentSimpleDto mapToSimpleDto(Department department);

    Department mapToEntity(DepartmentDto dto);

}
