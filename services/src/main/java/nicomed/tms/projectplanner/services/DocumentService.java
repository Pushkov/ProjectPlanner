package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DocumentService extends CrudDoubleDtoService<DocumentDto, DocumentSimpleDto, Long> {

    DocumentSignedDto findById(Long id);

    Page<DocumentSimpleDto> findPage(Integer page, Integer offset);

    Long count();

    void save(DocumentCreateDto dto);

    void save(Long id, DocumentCreateDto dto);

    void addProjectById(Long documentId, Long projectId);

    void removeProjectById(Long documentId, Long projectId);


    default List<DocumentSimpleDto> search(DocumentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }
}
