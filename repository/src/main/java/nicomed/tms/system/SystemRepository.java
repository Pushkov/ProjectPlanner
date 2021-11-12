package nicomed.tms.system;

import nicomed.tms.system.entity.SystemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepository extends JpaRepository<SystemOption, Long> {
}
