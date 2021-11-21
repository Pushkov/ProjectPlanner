package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.entity.Project;

public interface ProjectBaseMapper<D extends ProjectDto, T> extends BaseMapper<D, Project> {
}
