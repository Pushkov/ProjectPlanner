package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.SheetFormat;
import nicomed.tms.projectplanner.enums.Format;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetFormatRepository extends JpaRepository<SheetFormat, Long> {

    SheetFormat findByFormat(Format format);
}
