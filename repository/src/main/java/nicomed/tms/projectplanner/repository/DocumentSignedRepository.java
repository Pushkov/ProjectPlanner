package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentSignedRepository extends JpaRepository<DocumentSigned, Long>, SearchableRepository<DocumentSigned, Long> {
}
