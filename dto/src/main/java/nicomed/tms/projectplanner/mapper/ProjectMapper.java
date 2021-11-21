package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.entity.Project;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectMapper {

    ProjectDto mapToDto(Project project);

    Project mapToEntity(ProjectDto dtoShort);
}
