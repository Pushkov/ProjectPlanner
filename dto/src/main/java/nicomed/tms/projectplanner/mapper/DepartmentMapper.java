package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DepartmentDto;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);


    Department mapToEntity(DepartmentDto dto);

    DepartmentDto mapToDto(Department department);
}
