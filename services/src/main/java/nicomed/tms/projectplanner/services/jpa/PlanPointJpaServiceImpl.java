package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.PlanPoint;
import nicomed.tms.projectplanner.repository.PlanPointRepository;
import nicomed.tms.projectplanner.services.PlanPointService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PlanPointJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<PlanPoint, Long> implements PlanPointService {

    private final PlanPointRepository planPointRepository;

    @Override
    public PlanPoint findById(Long id) {
        return planPointRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(PlanPoint planPoint) {
        planPointRepository.save(planPoint);
    }

    @Override
    public Collection<PlanPoint> findAll() {
        return planPointRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        planPointRepository.deleteById(id);
    }

    @Override
    public JpaRepository<PlanPoint, Long> getRepository() {
        return planPointRepository;
    }
}
