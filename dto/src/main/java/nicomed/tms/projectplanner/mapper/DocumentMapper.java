package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DocumentMapper {

    DocumentSimpleDto mapToSimpleDto(Document document);

    @Mapping(target = "documentFormatDto", source = "document.documentFormats")
    DocumentDto mapToDto(Document document);

    Document mapToEntity(DocumentSimpleDto dto);

}
