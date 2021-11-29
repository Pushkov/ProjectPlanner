package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectDtoFull;

public interface ProjectService extends CrudService<ProjectDto, Long> {

    ProjectDtoFull findById(Long id);

    void save(Long id, ProjectDto dtoShort);
}
