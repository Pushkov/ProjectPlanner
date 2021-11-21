package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.project.ProjectDto;

public interface ProjectService extends CrudService<ProjectDto, Long> {

    void save(Long id, ProjectDto dtoShort);
}
