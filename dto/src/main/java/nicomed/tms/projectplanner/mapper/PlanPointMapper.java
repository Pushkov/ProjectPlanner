package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.entity.PlanPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PlanPointMapper {

    @Mapping(target = "year", source = "entity.plan.id.year")
    @Mapping(target = "month", source = "entity.plan.id.month")
    @Mapping(target = "departmentId", source = "entity.plan.id.department.id")
    @Mapping(target = "departmentName", source = "entity.project.department.name")
    @Mapping(target = "projectDesignation", source = "entity.project.designation")
    @Mapping(target = "projectName", source = "entity.project.name")
    PlanPointDto mapToDto(PlanPoint entity);

    PlanPoint mapToEntity(PlanPointDto dto);
}
