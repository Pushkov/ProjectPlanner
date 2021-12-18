package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.qualifier.DocumentFormatQualifier;
import nicomed.tms.projectplanner.qualifier.ProjectQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {
        DocumentFormatMapper.class,
        ProjectMapper.class,
        DocumentFormatQualifier.class,
        ProjectQualifier.class
})
public interface DocumentMapper {

    @Named("documentSimpleDto")
    DocumentSimpleDto mapToSimpleDto(Document document);

    @Mapping(target = "documentFormatDto", source = "documentFormats")
    DocumentSignedDto mapToDto(Document document);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentFormats", source = "dto.documentFormatDto", qualifiedByName = "documentFormatBy")
    @Mapping(target = "projects", source = "dto.projects", qualifiedByName = "projectByForList")
    Document mapToEntity(DocumentCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentFormats", source = "dto.documentFormatDto", qualifiedByName = "documentFormatBy")
    @Mapping(target = "projects", source = "dto.projects", qualifiedByName = "projectByForList")
    void mapToEntity(@MappingTarget Document document, DocumentCreateDto dto);

}
