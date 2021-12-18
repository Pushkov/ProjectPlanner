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
import nicomed.tms.projectplanner.services.*;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static nicomed.tms.projectplanner.repository.specification.ProjectSpecification.findByTerm;
import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class ProjectJpaServiceImpl extends AbstractDoubleDtoJpaService<ProjectDto, ProjectSimpleDto, Project, Long> implements ProjectService, SearcheableService<Project> {

    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;
    private final WorkshopService workshopService;
    private final TechnicalTaskService technicalTaskService;
    private final MemoService memoService;
    private final DepartmentService departmentService;
    private final EngineerService engineerService;

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
    public List<Project> findAllEntitiesById(Iterable ids) {
        return projectRepository.findAllById(ids);
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

    @Transactional
    @Override
    public void save(ProjectDto dto) {
        Project project = mapToEntity(dto);
        projectRepository.save(project);
        checkBasics(project);
        addDependProjects(project);
    }

    @Transactional
    @Override
    public void save(Long id, ProjectDto dto) {
        Project project = findEntityById(id);
        mapper.mapToEntity(project, dto);
        checkBasics(project);
        addDependProjects(project);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void checkBasics(Project project) {
        if (!Objects.isNull(project.getTask())) {
            project.setMemo(null);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void addDependProjects(Project project) {
        List<Project> baseProjects = project.getBasicProject();
        if (CollectionUtils.isNotEmpty(baseProjects)) {
            for (Project baseProject : baseProjects) {
                List<Project> projects = baseProject.getProjects();
                if (CollectionUtils.isNotEmpty(projects)) {
                    projects.add(project);
                } else {
                    baseProject.setProjects(asList(project));
                }
            }
        }
    }


    @Transactional
    @Override
    public void delete(Long id) {
        Project project = findEntityById(id);
        removeDependProjects(project);
        projectRepository.delete(project);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void removeDependProjects(Project project) {
        List<Project> baseProjects = project.getBasicProject();
        if (CollectionUtils.isNotEmpty(baseProjects)) {
            for (Project baseProject : baseProjects) {
                List<Project> projects = baseProject.getProjects();
                if (CollectionUtils.isNotEmpty(projects)) {
                    projects.remove(project);
                }
            }
        }
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
