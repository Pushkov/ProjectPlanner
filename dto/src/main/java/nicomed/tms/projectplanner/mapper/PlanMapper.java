package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {PlanPointMapper.class})
public interface PlanMapper {

    @Mapping(target = "year", source = "id.year")
    @Mapping(target = "month", source = "id.month")
    @Mapping(target = "departmentId", source = "id.department.id")
    @Mapping(target = "departmentName", source = "id.department.name")
    @Mapping(target = "planPointsDto", source = "plan.planPoints")
    PlanDto mapToDto(Plan plan);

    Plan mapToEntity(PlanDto dto);
}
