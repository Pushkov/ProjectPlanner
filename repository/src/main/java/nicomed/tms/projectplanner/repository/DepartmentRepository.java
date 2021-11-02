package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);

    Integer countAllByBasicDepartmentId(Long id);

    List<Department> findAllByBasicDepartmentId(Long id);

}
