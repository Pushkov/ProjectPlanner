package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.repository.specification.filter.ProjectFilter;

import java.util.List;

public interface ProjectService extends CrudDoubleDtoService<ProjectDto, ProjectSimpleDto, Long> {

    void save(Long id, ProjectDto dto);

    default List<ProjectSimpleDto> search(ProjectFilter projectFilter) {
        throw new UnsupportedOperationException();
    }
}
