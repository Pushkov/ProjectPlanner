package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
