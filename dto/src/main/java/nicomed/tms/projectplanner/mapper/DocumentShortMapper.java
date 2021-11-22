package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentDtoShort;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DocumentFormatMapper.class})
public interface DocumentShortMapper {


    @Mapping(target = "documentFormatDto", source = "document.documentFormats")
    DocumentDtoShort mapToDtoShort(Document document);

    @Mapping(target = "documentFormatDto", source = "document.documentFormats")
    @Mapping(target = "documentApprovalsDto", source = "document.documentApprovals")
    DocumentSignedDto mapToDtoSigned(DocumentSigned document);

}
