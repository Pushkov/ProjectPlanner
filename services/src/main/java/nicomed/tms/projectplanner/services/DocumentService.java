package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.entity.Document;

import java.util.List;

public interface DocumentService extends CrudService<Document, Long> {

    List<DocumentDto> findAllDto();

    DocumentDto findDtoById(Long id);
}
