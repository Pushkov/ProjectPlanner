package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.entity.PlanProject;
import nicomed.tms.projectplanner.mapper.PlanProjectMapper;
import nicomed.tms.projectplanner.repository.PlanProjectRepository;
import nicomed.tms.projectplanner.repository.specification.PlanProjectSpecification;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.PlanProjectFilter;
import nicomed.tms.projectplanner.services.PlanProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import nicomed.tms.projectplanner.services.exception.ExceptionsProducer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class PlanProjectServiceImpl implements PlanProjectService, SearcheableService<PlanProject> {

    private final PlanProjectRepository planProjectRepository;
    private final PlanProjectMapper mapper;

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
    public List<Integer> findAllYears() {
        return planProjectRepository.findAllYears();
    }

    @Override
    public List<Integer> findAllMonths() {
        return planProjectRepository.findAllMonths();
//        .stream()
//                .map(x -> LocalDate.of(1,x,1).getMonth().getValue())
//                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllDepartmentNames() {
        return planProjectRepository.findAllDepartmentNames();

    }
}
