package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DocumentFormatMapper {

    @Mapping(target = "format", source = "documentFormat.format.name")
    DocumentFormatDto mapToDto(DocumentFormat documentFormat);

    List<DocumentFormatDto> mapToListDto(List<DocumentFormat> documentFormats);

//    DocumentFormat mapToEntity(DocumentFormatDto documentFormatDto);
}
