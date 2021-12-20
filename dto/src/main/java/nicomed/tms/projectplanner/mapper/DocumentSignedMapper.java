package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.qualifier.DocumentApprovalsQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DocumentFormatMapper.class, DocumentApprovalsMapper.class, DocumentApprovalsQualifier.class})
public interface DocumentSignedMapper {

    DocumentSimpleDto mapToSimpleDto(DocumentSigned document);

    @Mapping(target = "documentFormatDto", source = "documentFormats")
    @Mapping(target = "documentApprovalsDto", source = "document", qualifiedByName = "documentApprovalsBy")
    DocumentSignedDto mapToDto(DocumentSigned document);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentFormats", source = "documentFormatDto")
    @Mapping(target = "documentApprovals", source = "dto.documentApprovalsDto")
    DocumentSigned mapToEntity(DocumentCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentFormats", source = "documentFormatDto")
    @Mapping(target = "documentApprovals", source = "dto.documentApprovalsDto")
    void mapToEntity(@MappingTarget DocumentSigned entity, DocumentCreateDto dto);


}
