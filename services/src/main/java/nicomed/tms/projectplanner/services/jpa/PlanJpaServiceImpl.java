package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPK;
import nicomed.tms.projectplanner.repository.PlanRepository;
import nicomed.tms.projectplanner.services.PlanService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
@JpaImpl
public class PlanJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Plan, PlanPK> implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public JpaRepository<Plan, PlanPK> getRepository() {
        return planRepository;
    }
}
