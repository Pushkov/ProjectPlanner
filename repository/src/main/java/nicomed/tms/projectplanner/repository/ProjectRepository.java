package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, SearchableRepository<Project, Long> {
}
