package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.qualifier.FormatQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {FormatMapper.class, FormatQualifier.class})
public interface DocumentFormatMapper {

    @Mapping(target = "formatDto", source = "format")
    DocumentFormatDto mapToDto(DocumentFormat entity);

    @Mapping(target = "format", ignore = true)
    DocumentFormat mapToEntity(DocumentFormatDto documentFormatDto);

    @Mapping(target = "format", source = "formatDto", qualifiedByName = "formatQualif")
//    @Mapping(target = "format", ignore = true)
    void mapToEntity(@MappingTarget DocumentFormat format, DocumentFormatDto documentFormatDto);
}
