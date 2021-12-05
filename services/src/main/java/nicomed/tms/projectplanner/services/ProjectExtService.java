package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;

public interface ProjectExtService<T extends ProjectSimpleDto> extends CrudService<T, Long> {

    void save(Long id, T dtoShort);
}
