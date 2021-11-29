package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.project.ProjectDto;

public interface ProjectExtService<T extends ProjectDto> extends CrudService<T, Long> {

    void save(Long id, T dtoShort);
}
