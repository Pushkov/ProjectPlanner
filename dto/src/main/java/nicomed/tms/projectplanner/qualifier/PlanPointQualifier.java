package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.entity.PlanPoint;
import nicomed.tms.projectplanner.repository.PlanPointRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlanPointQualifier extends AbstractEntityQualifier<PlanPoint, Long> {

    private final PlanPointRepository repository;

    @Override
    public JpaRepository<PlanPoint, Long> getRepository() {
        return repository;
    }

    public PlanPoint getBy(PlanPointDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

}

