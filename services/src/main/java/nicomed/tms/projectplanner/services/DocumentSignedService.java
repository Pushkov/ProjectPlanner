package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;

import java.util.Collection;

public interface DocumentSignedService extends CrudDoubleDtoService<DocumentSignedDto, DocumentSimpleDto, Long> {


    Collection<DocumentSignedDto> findAllSigned();

    void save(DocumentCreateDto dto);

    void save(Long id, DocumentCreateDto dto);

    void addProjectById(Long documentId, Long projectId);

    void removeProjectById(Long documentId, Long projectId);


}
