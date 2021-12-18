package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.entity.PlanPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PlanPointMapper {

    @Mapping(target = "year", source = "entity.plan.id.year")
    @Mapping(target = "month", source = "entity.plan.id.month")
    @Mapping(target = "departmentId", source = "entity.plan.id.department.id")
    @Mapping(target = "departmentName", source = "entity.plan.id.department.name")
    @Mapping(target = "projectDesignation", source = "entity.project.designation")
    @Mapping(target = "projectId", source = "entity.project.id")
    @Mapping(target = "projectName", source = "entity.project.name")
    @Mapping(target = "designerId", source = "entity.designer.id")
    @Mapping(target = "designerName", source = "entity.designer.lastName")
    PlanPointDto mapToDto(PlanPoint entity);

    @Mapping(target = "plan.id.year", source = "dto.year")
    @Mapping(target = "plan.id.month", source = "dto.month")
    @Mapping(target = "plan.id.department.id", source = "dto.departmentId")
    @Mapping(target = "designer", ignore = true)
    @Mapping(target = "project", ignore = true)
    PlanPoint mapToEntity(PlanPointDto dto);

    @Mapping(target = "plan.id.year", source = "dto.year")
    @Mapping(target = "plan.id.month", source = "dto.month")
    @Mapping(target = "plan.id.department.id", source = "dto.departmentId")
    @Mapping(target = "designer", ignore = true)
    @Mapping(target = "project", ignore = true)
    void mapToEntity(@MappingTarget PlanPoint point, PlanPointDto dto);
}
