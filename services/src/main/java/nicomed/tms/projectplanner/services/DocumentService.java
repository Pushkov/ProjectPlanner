package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;

import java.util.List;

public interface DocumentService extends CrudService<DocumentDto, Long> {

    default List<DocumentDto> search(DocumentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }
}
