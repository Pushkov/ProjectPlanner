package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
