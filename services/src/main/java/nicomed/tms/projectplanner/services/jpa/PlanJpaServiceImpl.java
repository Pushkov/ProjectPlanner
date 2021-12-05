package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPK;
import nicomed.tms.projectplanner.mapper.PlanMapper;
import nicomed.tms.projectplanner.repository.PlanRepository;
import nicomed.tms.projectplanner.services.PlanService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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
