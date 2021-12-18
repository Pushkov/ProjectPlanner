package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.dto.workshop.WorkshopSimpleDto;
import nicomed.tms.projectplanner.entity.Workshop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface WorkshopMapper {

    WorkshopSimpleDto mapToSimpleDto(Workshop workshop);

    @Mapping(target = "departmentName", source = "workshop.department.name")
    WorkshopDto mapToDto(Workshop workshop);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "technicalTasks", ignore = true)
    @Mapping(target = "memos", ignore = true)
    @Mapping(target = "projects", ignore = true)
    Workshop mapToEntity(WorkshopDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "technicalTasks", ignore = true)
    @Mapping(target = "memos", ignore = true)
    @Mapping(target = "projects", ignore = true)
    void mapToEntity(@MappingTarget Workshop workshop, WorkshopDto dto);

}
