package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.PlanProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlanProjectRepository extends JpaRepository<PlanProject, Long> {
    Optional<PlanProject> findById(Long id);

    List<PlanProject> findAll();

    @Query("SELECT DISTINCT year FROM PlanProject")
    List<Integer> findAllYears();

    @Query("SELECT DISTINCT month FROM PlanProject")
    List<Integer> findAllMonths();

    @Query("SELECT DISTINCT departmentName FROM PlanProject")
    List<String> findAllDepartmentNames();

}