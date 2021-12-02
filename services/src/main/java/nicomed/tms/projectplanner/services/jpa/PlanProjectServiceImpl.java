package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.entity.PlanProject;
import nicomed.tms.projectplanner.mapper.PlanProjectMapper;
import nicomed.tms.projectplanner.repository.PlanProjectRepository;
import nicomed.tms.projectplanner.services.PlanProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import nicomed.tms.projectplanner.services.exception.ExceptionsProducer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@JpaImpl
public class PlanProjectServiceImpl implements PlanProjectService {

    private final PlanProjectRepository planProjectRepository;
    private final PlanProjectMapper mapper;

    private PlanProject findEntityByID(Long id) {
        return planProjectRepository.findById(id)
                .orElseThrow(() -> ExceptionsProducer.throwNotFoundByIdException(PlanProject.class, id));
    }

    @Override
    public PlanProjectDto findById(Long id) {
        return mapper.mapToDto(findEntityByID(id));
    }

    @Override
    public Collection<PlanProjectDto> search(PlanProjectDto dtoExample) {
        ExampleMatcher caseInsensitiveExMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<PlanProject> example = Example.of(mapper.mapToEntity(dtoExample), caseInsensitiveExMatcher);
        return planProjectRepository.findAll(example).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<PlanProjectDto> findAll() {
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
