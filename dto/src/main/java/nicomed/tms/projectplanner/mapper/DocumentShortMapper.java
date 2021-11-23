package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentSignedSimpleDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
//@Mapper(uses = {DocumentFormatMapper.class})
public interface DocumentShortMapper {


    @Mapping(target = "documentFormatDto", source = "document.documentFormats")
    DocumentSimpleDto mapToDtoShort(Document document);

    @Mapping(target = "documentFormatDto", source = "document.documentFormats")
    @Mapping(target = "documentApprovalsDto", source = "document.documentApprovals")
    DocumentSignedSimpleDto mapToDtoSigned(DocumentSigned document);

}
