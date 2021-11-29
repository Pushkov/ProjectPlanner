package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.entity.Workshop;
import org.mapstruct.Mapper;

@Mapper
public interface WorkshopMapper {

    WorkshopDto mapToDto(Workshop workshop);

    Workshop mapToEntity(WorkshopDto dto);

}
