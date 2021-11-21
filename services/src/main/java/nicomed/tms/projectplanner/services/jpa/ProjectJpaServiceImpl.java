package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.ProjectDtoShort;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.mapper.ProjectMapper;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.services.util.MessageUtil.getNoEntityByIdFound;

@RequiredArgsConstructor
@JpaImpl
public class ProjectJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Project, Long> implements ProjectService, SearcheableService<Project> {

    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;

    @Override
    public JpaRepository<Project, Long> getRepository() {
        return projectRepository;
    }

    @Override
    public SearchableRepository<Project, ?> getSearchRepository() {
        return projectRepository;
    }

    @Override
    public List<ProjectDtoShort> findAllDtoShort() {
        return findAll().stream()
                .map(mapper::mapToDtoShort)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDtoShort findDtoShortById(Long id) {
        return mapper.mapToDtoShort(projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(getNoEntityByIdFound(Project.class, id))));
    }

    @Override
    public void save(ProjectDtoShort dtoShort) {
        projectRepository.save(mapper.mapDtoShortToEntity(dtoShort));
    }

    @Override
    public void save(Long id, ProjectDtoShort dtoShort) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(getNoEntityByIdFound(Project.class, id)));
        project.setName(dtoShort.getName());
        project.setDesignation(dtoShort.getDesignation());
        projectRepository.save(project);
    }
}
