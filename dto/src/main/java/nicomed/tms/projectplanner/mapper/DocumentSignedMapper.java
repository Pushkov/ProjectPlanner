package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DocumentSignedDto;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper()
public interface DocumentSignedMapper {

    DocumentSignedDto mapToDto(DocumentSigned document);
}
