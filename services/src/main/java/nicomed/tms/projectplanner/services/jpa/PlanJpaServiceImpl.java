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
    public PlanDto mapToDto(Plan entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Plan mapToEntity(PlanDto dto) {
        return mapper.mapToEntity(dto);
    }
}
