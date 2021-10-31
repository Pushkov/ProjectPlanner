package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
