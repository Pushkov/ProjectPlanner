package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.entity.Document;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DocumentMapper {

    DocumentDto mapToDto(Document document);

    Document mapToEntity(DocumentDto dto);


}
