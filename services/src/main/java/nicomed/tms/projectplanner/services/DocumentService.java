package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;

import java.util.List;

public interface DocumentService extends CrudService<Document, Long> {

    List<DocumentDto> findAllDto();

    DocumentDto findDtoById(Long id);

    default List<DocumentDto> search(DocumentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }
}
