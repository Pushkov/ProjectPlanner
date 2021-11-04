package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import nicomed.tms.projectplanner.services.ProjectService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProjectJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Project, Long> implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public JpaRepository<Project, Long> getRepository() {
        return projectRepository;
    }
}
