package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;

import java.util.Collection;

public interface DocumentSignedService extends CrudDoubleDtoService<DocumentSignedDto, DocumentSimpleDto, Long> {


    Collection<DocumentSignedDto> findAllSigned();

}
