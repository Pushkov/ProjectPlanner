package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {FormatMapper.class})
public interface DocumentFormatMapper {

    @Mapping(target = "formatDto", source = "format")
    DocumentFormatDto mapToDto(DocumentFormat entity);

    @Mapping(target = "format", ignore = true)
    DocumentFormat mapToEntity(DocumentFormatDto documentFormatDto);

    @Mapping(target = "format", ignore = true)
    void mapToEntity(@MappingTarget DocumentFormat format, DocumentFormatDto documentFormatDto);
}
