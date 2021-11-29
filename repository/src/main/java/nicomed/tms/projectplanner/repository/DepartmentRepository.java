package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long>, SearchableRepository<Department, Long> {

    Optional<Department> findByNameIgnoreCase(String name);

    Integer countAllByBasicDepartmentId(Long id);

    List<Department> findAllByBasicDepartmentId(Long id);

}
