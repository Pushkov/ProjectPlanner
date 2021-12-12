package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.mapper.ProjectMapper;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.ProjectFilter;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.repository.specification.ProjectSpecification.findByTerm;
import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.*;
import static nicomed.tms.projectplanner.services.util.MessageUtil.getNoEntityByIdFound;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class ProjectJpaServiceImpl extends AbstractDoubleDtoJpaService<ProjectDto, ProjectSimpleDto, Project, Long> implements ProjectService, SearcheableService<Project> {

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
    public ProjectDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Project findEntityById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> throwNotFoundByIdException(getEntityClass(), id));
    }

    @Override
    public Project findEntityByDesignation(String designation) {
        return projectRepository.findByDesignation(designation)
                .orElseThrow(() -> throwNotFoundByNameException(getEntityClass(), designation));
    }

    @Override
    public Project findEntityByName(String name) {
        return projectRepository.findByName(name)
                .orElseThrow(() -> throwNotFoundByNameException(getEntityClass(), name));
    }

    @Override
    public Collection<ProjectSimpleDto> findAll() {
        return super.findAll();
    }

    @Override
    public Page<ProjectSimpleDto> findPage(Integer page, Integer offset) {
        if (page >= 0 && offset > 0) {
            PageRequest pageRequest = PageRequest.of(page, offset);
            List<ProjectSimpleDto> documentSimpleDtos = projectRepository.findAll(pageRequest).stream()
                    .map(this::mapToSimpleDto)
                    .collect(Collectors.toList());
            return new PageImpl<>(documentSimpleDtos);
        }
        throw trowIncorrectPageAssignmentException("Incorrect page assignment");
    }

    @Override
    public void save(Long id, ProjectDto dtoShort) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(getNoEntityByIdFound(Project.class, id)));
        project.setName(dtoShort.getName());
        project.setDesignation(dtoShort.getDesignation());
        projectRepository.save(project);
    }

    @Override
    public List<ProjectSimpleDto> search(ProjectFilter projectFilter) {
        Specification<Project> specification = findByTerm(projectFilter.getTerm());
        return projectRepository.findAll(specification).stream()
                .map(this::mapToSimpleDto)
                .collect(Collectors.toList());
    }


    @Override
    public ProjectSimpleDto mapToSimpleDto(Project entity) {
        return mapper.mapToSimpleDto(entity);
    }

    @Override
    public ProjectDto mapToDto(Project entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Project mapToEntity(ProjectDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }
}
