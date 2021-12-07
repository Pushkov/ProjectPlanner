package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.DocumentFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DocumentFormatRepository extends JpaRepository<DocumentFormat, Long> {

    Collection<DocumentFormat> findAllByDocument_Id(Long documentId);

}
