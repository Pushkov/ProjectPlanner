package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DepartmentDto1;
import nicomed.tms.projectplanner.dto.DepartmentDtoSecond;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department mapToEntity(DepartmentDtoShort dto);

    DepartmentDtoShort mapToDto(Department department);


    Department mapToEntitySecond(DepartmentDtoSecond dto);

    @Mapping(target = "basicDep", source = "basicDepartment.name", defaultValue = " ")
    DepartmentDtoSecond mapToDtoSecond(Department department);

    Department mapToEntity1(DepartmentDto1 dto);

    //    @Mapping(target = "basicDepartmentName", source = "basicDepartment.name", defaultValue = " ")
    DepartmentDto1 mapToDto1(Department department);

}
