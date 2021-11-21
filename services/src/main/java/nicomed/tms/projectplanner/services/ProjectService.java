package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.ProjectDtoShort;
import nicomed.tms.projectplanner.entity.Project;

import java.util.List;

public interface ProjectService extends CrudService<Project, Long> {

    List<ProjectDtoShort> findAllDtoShort();

    ProjectDtoShort findDtoShortById(Long id);

    void save(ProjectDtoShort dtoShort);

    void save(Long id, ProjectDtoShort dtoShort);
}
