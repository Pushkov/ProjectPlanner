package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
