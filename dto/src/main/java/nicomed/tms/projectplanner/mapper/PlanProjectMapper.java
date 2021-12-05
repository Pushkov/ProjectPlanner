package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.entity.PlanProject;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PlanProjectMapper {

    PlanProjectDto mapToDto(PlanProject entity);

    PlanProject mapToEntity(PlanProjectDto dto);
}
