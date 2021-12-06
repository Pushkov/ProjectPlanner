package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPK;
import nicomed.tms.projectplanner.mapper.PlanMapper;
import nicomed.tms.projectplanner.repository.PlanRepository;
import nicomed.tms.projectplanner.services.PlanService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.trowIncorrectPageAssignmentException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class PlanJpaServiceImpl extends AbstractJpaService<PlanDto, Plan, PlanPK> implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper mapper;

    @Override
    public JpaRepository<Plan, PlanPK> getRepository() {
        return planRepository;
    }

    @Override
    public Collection<PlanDto> findAll() {
        return super.findAll();
    }

    @Override
    public Page<PlanDto> findPage(Integer page, Integer offset) {
        if (page >= 0 && offset > 0) {
            PageRequest pageRequest = PageRequest.of(page, offset);
            List<PlanDto> documentSimpleDtos = planRepository.findAll(pageRequest).stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
            return new PageImpl<>(documentSimpleDtos);
        }
        throw trowIncorrectPageAssignmentException("Incorrect page assignment");
    }

    @Override
    public PlanDto findById(PlanPK planPK) {
        return super.findById(planPK);
    }

    @Override
    public PlanDto mapToDto(Plan entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Plan mapToEntity(PlanDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Plan> getEntityClass() {
        return Plan.class;
    }
}
