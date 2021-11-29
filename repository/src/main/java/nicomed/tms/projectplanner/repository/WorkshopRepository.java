package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
