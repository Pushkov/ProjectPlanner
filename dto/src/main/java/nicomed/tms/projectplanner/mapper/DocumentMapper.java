package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {DocumentFormatMapper.class, DocumentApprovalsMapper.class})
public interface DocumentMapper {

    @Mapping(target = "documentApprovalsDto", source = "document.documentApprovals")
    @Mapping(target = "documentFormatDto", source = "document.documentFormats")
    DocumentDto mapToDto(Document document);

    List<DocumentDto> mapToListDto(List<Document> documentList);
//    DocumentFormat mapToEntity(DocumentFormatDto documentFormatDto);
}
