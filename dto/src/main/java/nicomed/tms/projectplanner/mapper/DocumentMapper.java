package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DocumentFormatMapper.class})
public interface DocumentMapper {

    DocumentSimpleDto mapToSimpleDto(Document document);

    @Mapping(target = "documentFormatDto", source = "documentFormats")
    DocumentSignedDto mapToDto(Document document);

    Document mapToEntity(DocumentSignedDto dto);

}
