package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PlanPointRepository extends JpaRepository<PlanPoint, Long> {

    Collection<PlanPoint> findAlByPlan(Plan plan);
}
