package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.repository.specification.filter.ProjectFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService extends CrudDoubleDtoService<ProjectDto, ProjectSimpleDto, Long> {

    void save(Long id, ProjectDto dto);

    Project findEntityById(Long id);

    Project findEntityByDesignation(String designation);

    Project findEntityByName(String name);

    Page<ProjectSimpleDto> findPage(Integer page, Integer offset);

    default List<ProjectSimpleDto> search(ProjectFilter projectFilter) {
        throw new UnsupportedOperationException();
    }
}
