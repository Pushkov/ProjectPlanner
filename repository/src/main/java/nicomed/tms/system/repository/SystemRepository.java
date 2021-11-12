package nicomed.tms.system.repository;

import nicomed.tms.system.entity.SystemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<SystemOption, Long> {
}
