package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineerRepository extends JpaRepository<Engineer, Long> {
}
