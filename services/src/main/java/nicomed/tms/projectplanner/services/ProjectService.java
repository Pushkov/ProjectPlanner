package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;

public interface ProjectService extends CrudService<ProjectSimpleDto, Long> {

    ProjectDto findById(Long id);

    void save(Long id, ProjectSimpleDto dtoShort);
}
