package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DocumentFormatMapper.class})
public interface DocumentMapper {

    DocumentSimpleDto mapToSimpleDto(Document document);

    @Mapping(target = "documentFormatDto", source = "documentFormats")
    DocumentSignedDto mapToDto(Document document);

    @Mapping(target = "id", ignore = true)
    DocumentSigned mapToEntity(DocumentCreateDto dto);

    @Mapping(target = "id", ignore = true)
    void mapToEntity(@MappingTarget Document document, DocumentCreateDto dto);

}
