package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectDtoFull;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.mapper.ProjectMapper;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static nicomed.tms.projectplanner.services.util.MessageUtil.getNoEntityByIdFound;

@RequiredArgsConstructor
@JpaImpl
public class ProjectJpaServiceImpl extends AbstractJpaService<ProjectDto, Project, Long> implements ProjectService, SearcheableService<Project> {

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
    public void save(Long id, ProjectDto dtoShort) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(getNoEntityByIdFound(Project.class, id)));
        project.setName(dtoShort.getName());
        project.setDesignation(dtoShort.getDesignation());
        projectRepository.save(project);
    }

    @Transactional
    public ProjectDtoFull findById(Long id) {
        return projectRepository.findById(id)
                .map(e -> mapToDto(ProjectDtoFull.builder().build(), e))
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
    }

    @Override
    public ProjectDto mapToDto(Project entity) {
        return mapper.mapToDto(ProjectDto.builder().build(), entity);
    }

    @Override
    public Project mapToEntity(ProjectDto dto) {
        return mapper.mapToEntity(dto);
    }

    public ProjectDtoFull mapToDto(ProjectDtoFull dtoFull, Project project) {
        return mapper.mapToDto(dtoFull, project);
    }

    @Override
    public String getEntityClassName() {
        return Project.class.getSimpleName();
    }
}
