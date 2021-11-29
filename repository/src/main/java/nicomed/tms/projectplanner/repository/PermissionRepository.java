package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findByRoles_Id(Long id);

    List<Permission> findAllByNameContainsIgnoreCase(String name);


}

