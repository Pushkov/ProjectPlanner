package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long>, SearchableRepository<Document, Long> {
}
