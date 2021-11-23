package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.DocumentSignedSimpleDto;

public interface DocumentSignedService extends CrudService<DocumentSignedSimpleDto, Long> {

    Long count();
}
