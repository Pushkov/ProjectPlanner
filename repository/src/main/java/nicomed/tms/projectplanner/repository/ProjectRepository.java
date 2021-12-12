package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long>, SearchableRepository<Project, Long> {

    Optional<Project> findByDesignation(String designation);

    Optional<Project> findByName(String name);
}
