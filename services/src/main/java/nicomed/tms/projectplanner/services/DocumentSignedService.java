package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.DocumentSignedDto;
import nicomed.tms.projectplanner.entity.DocumentSigned;

import java.util.List;

public interface DocumentSignedService extends CrudService<DocumentSigned, Long> {

    DocumentSignedDto findDtoById(Long id);

    List<DocumentSignedDto> findAllDto();

    Long count();

}
