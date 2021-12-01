package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.PlanProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanProjectRepository extends JpaRepository<PlanProject, Long> {
    Optional<PlanProject> findById(Long id);

    List<PlanProject> findAll();
}