package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
@JpaImpl
public class ProjectJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Project, Long> implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public JpaRepository<Project, Long> getRepository() {
        return projectRepository;
    }
}
