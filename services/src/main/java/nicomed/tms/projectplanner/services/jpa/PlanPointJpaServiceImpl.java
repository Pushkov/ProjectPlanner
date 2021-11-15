package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.PlanPoint;
import nicomed.tms.projectplanner.repository.PlanPointRepository;
import nicomed.tms.projectplanner.services.PlanPointService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
@JpaImpl
public class PlanPointJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<PlanPoint, Long> implements PlanPointService {

    private final PlanPointRepository planPointRepository;

    @Override
    public JpaRepository<PlanPoint, Long> getRepository() {
        return planPointRepository;
    }
}
