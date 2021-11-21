package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DepartmentDto;
import nicomed.tms.projectplanner.dto.DepartmentDtoSecond;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DepartmentMapper {

    @Mapping(target = "basicDep", source = "basicDepartment.name", defaultValue = " ")
    DepartmentDtoSecond mapToDtoSecond(Department department);

    DepartmentDto mapToDto(Department department);

    Department mapToEntity(DepartmentDto dto);

}
