package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;

public interface DocumentSignedService extends CrudService<DocumentSignedDto, Long> {

    Long count();
}
