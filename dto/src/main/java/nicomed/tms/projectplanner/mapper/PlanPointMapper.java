package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.entity.PlanPoint;
import org.mapstruct.Mapper;

@Mapper
public interface PlanPointMapper {

    PlanPointDto mapToDto(PlanPoint planPoint);

    PlanPoint mapToEntity(PlanPointDto dto);
}
