package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DepartmentDto1;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department mapToEntity(DepartmentDtoShort dto);

    DepartmentDtoShort mapToDto(Department department);

    Department mapToEntity1(DepartmentDto1 dto);

    DepartmentDto1 mapToDto1(Department department);
}
