package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DocumentSignedDto;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DocumentFormatMapper.class, DocumentApprovalsMapper.class})
public interface DocumentSignedMapper {

    @Mapping(target = "documentApprovalsDto", source = "document.documentApprovals")
    @Mapping(target = "documentFormatDto", source = "document.documentFormats")
    DocumentSignedDto mapToDto(DocumentSigned document);
}
