package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.entity.PlanProject;
import nicomed.tms.projectplanner.mapper.DepartmentMapper;
import nicomed.tms.projectplanner.mapper.PlanProjectMapper;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.repository.PlanProjectRepository;
import nicomed.tms.projectplanner.repository.specification.PlanProjectSpecification;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.PlanProjectFilter;
import nicomed.tms.projectplanner.services.PlanProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import nicomed.tms.projectplanner.services.exception.ExceptionsProducer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.trowIncorrectPageAssignmentException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class PlanProjectServiceImpl implements PlanProjectService, SearcheableService<PlanProject> {

    private final PlanProjectRepository planProjectRepository;
    private final PlanProjectMapper mapper;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public SearchableRepository<PlanProject, ?> getSearchRepository() {
        return planProjectRepository;
    }

    private PlanProject findEntityByID(Long id) {
        return planProjectRepository.findById(id)
                .orElseThrow(() -> ExceptionsProducer.throwNotFoundByIdException(PlanProject.class, id));
    }

    @Override
    public PlanProjectDto findById(Long id) {
        return mapper.mapToDto(findEntityByID(id));
    }

    @Override
    public List<PlanProjectDto> search(PlanProjectFilter filter) {
        Specification<PlanProject> specification = PlanProjectSpecification.search(filter);
        return planProjectRepository.findAll(specification).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<PlanProjectDto> findAll() {
        return planProjectRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PlanProjectDto> findPage(Integer page, Integer offset) {
        if (page >= 0 && offset > 0) {
            PageRequest pageRequest = PageRequest.of(page, offset);
            List<PlanProjectDto> documentSimpleDtos = planProjectRepository.findAll(pageRequest).stream()
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
            return new PageImpl<>(documentSimpleDtos);
        }
        throw trowIncorrectPageAssignmentException("Incorrect page assignment");
    }

    @Override
    public List<Integer> findAllYears() {
        return planProjectRepository.findAllYears();
    }

    @Override
    public List<Integer> findAllMonths() {
        return planProjectRepository.findAllMonths();
    }

    @Override
    public List<DepartmentSimpleDto> findAllDepartmentsFromView() {
        List<Long> departmentIds = planProjectRepository.findAllDepartmentsId();
        List<Department> departments = departmentRepository.findAllById(departmentIds);
        return departments.stream()
                .map(departmentMapper::mapToSimpleDto)
                .collect(Collectors.toList());

    }
}
