package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalTaskRepository extends JpaRepository<TechnicalTask, Long>, SearchableRepository<TechnicalTask, Long> {
}
