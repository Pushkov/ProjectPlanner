package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
