package nicomed.tms.system.repository;

import nicomed.tms.system.entity.SystemLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLogRepository extends JpaRepository<SystemLogEntity, Long> {
}
