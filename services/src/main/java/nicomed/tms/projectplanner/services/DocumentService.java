package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DocumentService extends CrudDoubleDtoService<DocumentDto, DocumentSimpleDto, Long> {

    DocumentSignedDto findById(Long id);

    Page<DocumentSimpleDto> findPage(Integer page, Integer offset);

    default List<DocumentSimpleDto> search(DocumentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }
}
