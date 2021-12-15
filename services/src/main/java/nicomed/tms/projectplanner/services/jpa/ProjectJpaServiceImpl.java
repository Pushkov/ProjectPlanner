package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.project.ProjectApprovalsDto;
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
        if (!Objects.isNull(dto.getProjectApprovalsDto())) {
            setApproveEngineers(dto.getProjectApprovalsDto(), project);
        }
        setWorkshop(dto.getWorkshopId(), project);
        setBasics(dto, project);
        setDepartment(dto.getDepartmentId(), project);
        setBasicProjectsList(dto.getBasicProject(), project);
    }

    @Transactional
    @Override
    public void save(Long id, ProjectDto dto) {
        Project project = findEntityById(id);
        mapper.mapToEntity(project, dto);
        if (!Objects.isNull(dto.getProjectApprovalsDto())) {
            setApproveEngineers(dto.getProjectApprovalsDto(), project);
        }
        setWorkshop(dto.getWorkshopId(), project);
        setBasics(dto, project);
        setDepartment(dto.getDepartmentId(), project);
        setBasicProjectsList(dto.getBasicProject(), project);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setApproveEngineers(ProjectApprovalsDto approves, Project project) {
        if (!Objects.isNull(approves.getDesignerId())) {
            project.getProjectApprovals().setDesigner(engineerService.findEntityById(approves.getDesignerId()));
        } else {
            project.getProjectApprovals().setDesigner(null);
            project.getProjectApprovals().setDesignerSign(null);
        }
        if (!Objects.isNull(approves.getVerifierId())) {
            project.getProjectApprovals().setVerifier(engineerService.findEntityById(approves.getVerifierId()));
        } else {
            project.getProjectApprovals().setVerifier(null);
            project.getProjectApprovals().setVerifierSign(null);
        }
        if (!Objects.isNull(approves.getNormControlId())) {
            project.getProjectApprovals().setNormControl(engineerService.findEntityById(approves.getNormControlId()));
        } else {
            project.getProjectApprovals().setNormControl(null);
            project.getProjectApprovals().setNormControlSign(null);
        }
        if (!Objects.isNull(approves.getAgreeId())) {
            project.getProjectApprovals().setAgree(engineerService.findEntityById(approves.getAgreeId()));
        } else {
            project.getProjectApprovals().setAgree(null);
            project.getProjectApprovals().setAgreeSign(null);
        }
        if (!Objects.isNull(approves.getApproveId())) {
            project.getProjectApprovals().setApprove(engineerService.findEntityById(approves.getApproveId()));
        } else {
            project.getProjectApprovals().setApprove(null);
            project.getProjectApprovals().setApproveSign(null);
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setBasics(ProjectDto dto, Project project) {
        if (!Objects.isNull(dto.getTask())) {
            setTask(dto.getTask().getId(), project);

        } else if (!Objects.isNull(dto.getMemo())) {
            setMemo(dto.getMemo().getId(), project);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setWorkshop(Long workshopId, Project project) {
        if (!Objects.isNull(workshopId)) {
            project.setWorkshop(workshopService.findEntityById(workshopId));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setTask(Long taskId, Project project) {
        if (!Objects.isNull(taskId) && taskId > 0) {
            project.setTask(technicalTaskService.findEntityById(taskId));
            project.setMemo(null);
        } else {
            project.setTask(null);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setMemo(Long memoId, Project project) {
        if (!Objects.isNull(memoId) && memoId > 0) {
            project.setMemo(memoService.findEntityById(memoId));
            project.setTask(null);
        } else {
            project.setMemo(null);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setDepartment(Long depId, Project project) {
        if (!Objects.isNull(depId)) {
            project.setDepartment(departmentService.findEntityById(depId));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setBasicProjectsList(List<ProjectSimpleDto> basicProjectIds, Project project) {
        if (!CollectionUtils.isEmpty(basicProjectIds)) {
            List<Long> listId = basicProjectIds.stream()
                    .map(ProjectSimpleDto::getId)
                    .collect(Collectors.toList());
            List<Project> basicProjects = projectRepository.findAllById(listId);
            for (Project basicProject : basicProjects) {
                basicProject.getProjects().add(project);
            }
            project.setBasicProject(basicProjects);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Project project = findEntityById(id);
        List<Project> basicProjectsList = project.getBasicProject();
        for (Project basePrj : basicProjectsList) {
            basePrj.getProjects().remove(project);
        }
        List<Project> projectsList = project.getProjects();
        for (Project prj : projectsList) {
            prj.getBasicProject().remove(project);
        }
        projectRepository.delete(project);
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
