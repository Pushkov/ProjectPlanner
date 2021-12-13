package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;

import java.util.Collection;
import java.util.List;

public interface DocumentFormatService extends CrudService<DocumentFormatDto, Long> {

    void save(DocumentFormat documentFormat);

    DocumentFormat findEntityById(Long id);

    Collection<DocumentFormatDto> findByDocumentId(Long id);

    List<DocumentFormat> findAllById(Iterable ids);

    Collection<DocumentFormat> findAllEntitiesByDocumentId(Long id);
}
