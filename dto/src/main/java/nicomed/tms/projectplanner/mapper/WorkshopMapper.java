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

    WorkshopDto mapToDto(Workshop workshop);

    @Mapping(target = "id", ignore = true)
    Workshop mapToEntity(WorkshopDto dto);

    @Mapping(target = "id", ignore = true)
    void mapToEntity(@MappingTarget Workshop workshop, WorkshopDto dto);

}
