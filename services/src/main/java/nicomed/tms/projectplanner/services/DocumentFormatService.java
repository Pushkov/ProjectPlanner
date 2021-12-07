package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;

import java.util.Collection;

public interface DocumentFormatService extends CrudService<DocumentFormatDto, Long> {

    void save(DocumentFormat documentFormat);

    Collection<DocumentFormatDto> findByDocumentId(Long id);
}
