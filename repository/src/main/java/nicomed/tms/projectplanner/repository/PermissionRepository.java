package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.enums.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByName(UserPermission name);

    List<Permission> findAllByName(String subName);
}
