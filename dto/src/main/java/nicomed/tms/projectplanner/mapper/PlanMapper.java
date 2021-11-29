package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.Plan;
import org.mapstruct.Mapper;

@Mapper
public interface PlanMapper {

    PlanDto mapToDto(Plan plan);

    Plan mapToEntity(PlanDto dto);
}
