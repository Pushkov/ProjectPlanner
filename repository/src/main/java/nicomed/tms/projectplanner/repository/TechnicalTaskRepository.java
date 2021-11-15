package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.TechnicalTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalTaskRepository extends JpaRepository<TechnicalTask, Long> {
}
