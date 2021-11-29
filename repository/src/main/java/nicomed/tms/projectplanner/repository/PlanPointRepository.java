package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.PlanPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanPointRepository extends JpaRepository<PlanPoint, Long> {
}
