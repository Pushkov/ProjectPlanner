package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DepartmentMapper {

    @Mapping(target = "basicDep", source = "basicDepartment.name")
    @Mapping(target = "basicDepId", source = "basicDepartment.id")
    DepartmentDto mapToDto(Department department);

    @Named("departmentSimple")
    DepartmentSimpleDto mapToSimpleDto(Department department);

    @Mapping(target = "basicDepartment", ignore = true)
    @Mapping(target = "id", ignore = true)
    Department mapToEntity(DepartmentDto dto);

    @Mapping(target = "basicDepartment", ignore = true)
    @Mapping(target = "id", ignore = true)
    void mapToEntity(@MappingTarget Department department, DepartmentDto dto);

}
