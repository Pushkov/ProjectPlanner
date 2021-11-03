package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByPermissions(Permission permission);
}
