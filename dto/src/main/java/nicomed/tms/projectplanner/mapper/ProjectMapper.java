package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.ProjectDtoShort;
import nicomed.tms.projectplanner.entity.Project;
import org.mapstruct.Mapper;

@Mapper(uses = {EngineerMapper.class})
public interface ProjectMapper {

    ProjectDtoShort mapToDtoShort(Project project);

    Project mapDtoShortToEntity(ProjectDtoShort dtoShort);
}
