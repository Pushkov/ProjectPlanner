package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DocumentFormatMapper {

    //    @Mapping(target = "format", source = "format.name")
    DocumentFormatDto mapToDto(DocumentFormat documentFormat);

//    DocumentFormat mapToEntity(DocumentFormatDto documentFormatDto);
}
