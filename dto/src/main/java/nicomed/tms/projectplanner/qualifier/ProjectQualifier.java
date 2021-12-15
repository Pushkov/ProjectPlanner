package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectForListDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectQualifier extends AbstractEntityQualifier<Project, Long> {

    private final ProjectRepository repository;

    @Override
    public JpaRepository<Project, Long> getRepository() {
        return repository;
    }

    public Project getBy(ProjectDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Project getBySimple(ProjectSimpleDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Project getByForList(ProjectForListDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }
}

